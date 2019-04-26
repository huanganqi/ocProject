package com.online.college.portal.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.core.notice.entity.Notice;
import com.online.college.core.work.entity.StudentHomeWork;
import com.online.college.core.work.entity.TeacherHomeWork;
import com.online.college.core.work.service.StudentWorkService;
import com.online.college.core.work.service.TeacherWorkService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName WorkController
 * @Description TODO
 * @Author like
 * @Data 2019/3/25 15:02
 * @Version 1.0
 **/
@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseBusiness courseBusiness;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private TeacherWorkService teacherWorkService;

    @Autowired
    private StudentWorkService studentWorkService;

    @RequestMapping("/list")
    public ModelAndView list(TeacherHomeWork teacherHomeWork, TailPage<TeacherHomeWork> page) {
        ModelAndView mv = new ModelAndView("work/list");

        Long courseId = Long.valueOf(1);

        //获取课程
        Course course = courseService.getById(courseId);
        if (null == course)
            return new ModelAndView("error/404");

        //获取讲师
        AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());
        if (null != courseTeacher && StringUtils.isNotEmpty(courseTeacher.getHeader())) {
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        mv.addObject("courseTeacher", courseTeacher);


        if (StringUtils.isNotEmpty(teacherHomeWork.getTitle())) {
            teacherHomeWork.setTitle(teacherHomeWork.getTitle().trim());
        } else {
            teacherHomeWork.setTitle(null);
        }

//        只查询正常发布的作业
        teacherHomeWork.setStatus(1);

        page = teacherWorkService.selectTeacherWorkForPage(teacherHomeWork, page);

        mv.addObject("queryEntity", teacherHomeWork);
        mv.addObject("page", page);
        return mv;
    }

    @RequestMapping(value = "/commit")
    @ResponseBody
    public ModelAndView commit(TeacherHomeWork teacherHomeWork) {
        ModelAndView mv = new ModelAndView("work/commit");

        //获取当前用户
        Long userId = SessionContext.getUserId();
        AuthUser authUser = authUserService.getById(userId);


        Long courseId = Long.valueOf(1);

        //获取课程
        Course course = courseService.getById(courseId);
        if (null == course)
            return new ModelAndView("error/404");

        //获取讲师
        AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());
        if (null != courseTeacher && StringUtils.isNotEmpty(courseTeacher.getHeader())) {
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        mv.addObject("courseTeacher", courseTeacher);
        TeacherHomeWork teacherHomeWork1 = teacherWorkService.selectById(teacherHomeWork.getId());
        mv.addObject("info", teacherHomeWork1);

        StudentHomeWork studentHomeWork = studentWorkService.selectByWorkIdAndUserId(teacherHomeWork.getId(), authUser.getUsername());
        mv.addObject("work", studentHomeWork);

        return mv;
    }

    @RequestMapping(value = "/stuSave")
    @ResponseBody
    public String stuSave(StudentHomeWork studentHomeWork) {
        //获取当前用户
        Long userId = SessionContext.getUserId();
        AuthUser authUser = authUserService.getById(userId);
        StudentHomeWork studentHomeWork1 = studentWorkService.selectByWorkIdAndUserId(studentHomeWork.getWorkId(), authUser.getUsername());
        if (studentHomeWork1 != null) {
            studentHomeWork.setId(studentHomeWork1.getId());
        }
        boolean bo = false;
        bo = studentWorkService.save(studentHomeWork);
        if (bo) {
            return new JsonView(0).toString();
        } else {
            return new JsonView(1, "保存失败", null).toString();
        }
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public ModelAndView info(TeacherHomeWork teacherHomeWork) {
        ModelAndView mv = new ModelAndView("work/info");
        TeacherHomeWork teacherHomeWork1 = teacherWorkService.selectById(teacherHomeWork.getId());
        mv.addObject("info", teacherHomeWork1);
        return mv;
    }

}
