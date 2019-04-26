package com.online.college.opt.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.course.domain.Course;
import com.online.college.core.notice.entity.Notice;
import com.online.college.core.work.entity.StudentHomeWork;
import com.online.college.core.work.entity.TeacherHomeWork;
import com.online.college.core.work.service.StudentWorkService;
import com.online.college.core.work.service.TeacherWorkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName WorkController
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 17:15
 * @Version 1.0
 **/
@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private TeacherWorkService teacherWorkService;

    @Autowired
    private StudentWorkService studentWorkService;

    @RequestMapping(value = "/list")
    public ModelAndView list(TeacherHomeWork teacherHomeWork, TailPage<TeacherHomeWork> page) {
        ModelAndView mv = new ModelAndView("cms/work/workPageList");
        mv.addObject("curNav", "teacherWork");

        if (StringUtils.isNotEmpty(teacherHomeWork.getTitle())) {
            teacherHomeWork.setTitle(teacherHomeWork.getTitle().trim());
        } else {
            teacherHomeWork.setTitle(null);
        }

        if (Integer.valueOf(-1).equals(teacherHomeWork.getStatus())) {
            teacherHomeWork.setStatus(null);
        }
        // 1. 查询教师发布的作业要求
        page = teacherWorkService.selectTeacherWorkForPage(teacherHomeWork, page);
        mv.addObject("queryEntity", teacherHomeWork);
        mv.addObject("page", page);
        // 2. 查询学生提交的作业
        StudentHomeWork studentHomeWork = new StudentHomeWork();
        TailPage<StudentHomeWork> studentHomeWorkTailPage = new TailPage<>();
        studentHomeWorkTailPage = studentWorkService.selectStudentWorkForPage(studentHomeWork, studentHomeWorkTailPage);
        mv.addObject("studentWork", teacherHomeWork);
        mv.addObject("studentPage", studentHomeWorkTailPage);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNotice() {
        ModelAndView mv = new ModelAndView("cms/work/add");
        mv.addObject("curNav", "teacherWork");
        return mv;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(TeacherHomeWork teacherHomeWork) {
        boolean bo = false;
        bo = teacherWorkService.save(teacherHomeWork);
        if (bo) {
            return new JsonView(0).toString();
        } else {
            return new JsonView(1, "保存失败", null).toString();
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public ModelAndView info(TeacherHomeWork teacherHomeWork) {
        ModelAndView mv = new ModelAndView("cms/work/update");
        TeacherHomeWork teacherHomeWork1 = teacherWorkService.selectById(teacherHomeWork.getId());
        mv.addObject("info", teacherHomeWork1);
        return mv;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(TeacherHomeWork teacherHomeWork) {
        teacherWorkService.delete(teacherHomeWork.getId());
        return new JsonView(0).toString();
    }

    @RequestMapping(value = "/stuSave")
    @ResponseBody
    public String stuSave(StudentHomeWork studentHomeWork) {

        studentHomeWork.setWorkId(null);
        studentHomeWork.setStuId(null);
        boolean bo = false;
        bo = studentWorkService.saveScore(studentHomeWork);
        if (bo) {
            return new JsonView(0).toString();
        } else {
            return new JsonView(1, "保存失败", null).toString();
        }
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public ModelAndView read(StudentHomeWork studentHomeWork) {
        ModelAndView mv = new ModelAndView("cms/work/studentHomeWork");

        StudentHomeWork studentHomeWork1 = studentWorkService.selectById(studentHomeWork.getId());
        mv.addObject("info", studentHomeWork1);
        return mv;
    }

}
