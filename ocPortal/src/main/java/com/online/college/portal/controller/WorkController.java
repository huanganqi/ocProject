package com.online.college.portal.controller;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/list")
    public ModelAndView list(){
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

        return mv;
    }
}
