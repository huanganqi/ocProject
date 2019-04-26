package com.online.college.portal.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.core.file.entity.FileParent;
import com.online.college.core.file.entity.FileReal;
import com.online.college.core.file.service.FileParentService;
import com.online.college.core.file.service.FileRealService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ResController
 * @Description 资源
 * @Author like
 * @Data 2019/3/25 15:06
 * @Version 1.0
 **/
@Controller
@RequestMapping("/res")
public class ResController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseBusiness courseBusiness;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private FileRealService fileRealService;

    @Autowired
    private FileParentService fileParentService;

    @RequestMapping("/list")
    public ModelAndView list(FileParent fileParent, TailPage<FileParent> page){
        ModelAndView mv = new ModelAndView("res/list");

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

        // 1. 查询目录
        page = fileParentService.findAllForPage(fileParent, page);
        mv.addObject("queryEntity", fileParent);
        mv.addObject("page", page);


        // 2. 查询文件
        FileReal fileReal = new FileReal();
        TailPage<FileReal> fileRealTailPage = new TailPage<>();
        fileRealTailPage = fileRealService.selectForPage(fileReal, fileRealTailPage);
        mv.addObject("fileReal", fileParent);
        mv.addObject("filePage", fileRealTailPage);
        return mv;
    }
}
