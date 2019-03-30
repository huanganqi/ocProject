package com.online.college.portal.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.core.notice.entity.Notice;
import com.online.college.core.notice.service.NoticeService;
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
 * @ClassName NoticeController
 * @Description 通知公告
 * @Author like
 * @Data 2019/3/22 16:22
 * @Version 1.0
 **/
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseBusiness courseBusiness;

    @Autowired
    private IAuthUserService authUserService;

    @RequestMapping(value = "/list")
    public ModelAndView queryNotice(Notice notice, TailPage<Notice> page) {
        ModelAndView mv = new ModelAndView("notice/list");

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


        if (StringUtils.isNotEmpty(notice.getTitle())) {
            notice.setTitle(notice.getTitle().trim());
        } else {
            notice.setTitle(null);
        }

        if (Integer.valueOf(-1).equals(notice.getStatus())) {
            notice.setStatus(null);
        }
        page = noticeService.queryPage(notice, page);


//        List<Notice> noticeList = noticeService.queryAll();
        mv.addObject("queryEntity", notice);
        mv.addObject("page", page);
        return mv;
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public ModelAndView info(Notice notice){
        ModelAndView mv = new ModelAndView("notice/info");
        Notice notice1=noticeService.queryById(notice.getId());
        mv.addObject("info", notice1);
        return mv;
    }


}
