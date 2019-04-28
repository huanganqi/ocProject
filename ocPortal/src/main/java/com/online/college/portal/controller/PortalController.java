package com.online.college.portal.controller;

import java.util.List;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.web.SessionContext;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.core.menu.domain.Menu;
import com.online.college.core.menu.service.IMenuService;
import com.online.college.core.sign.entity.Sign;
import com.online.college.core.sign.service.SignService;
import com.online.college.core.user.domain.UserCourseSection;
import com.online.college.core.user.service.IUserCourseSectionService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.consts.CourseEnum;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;

/**
 * 网站主页
 */
@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private IConstsSiteCarouselService siteCarouselService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private ICourseBusiness courseBusiness;

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private SignService signService;


//	/**
//	 * 首页
//	 */
//	@RequestMapping("/index1")
//	public ModelAndView index(){
//		ModelAndView mv = new ModelAndView("index");
//
//		//加载轮播
//		List<ConstsSiteCarousel> carouselList = siteCarouselService.queryCarousels(4);
//		mv.addObject("carouselList", carouselList);
//
//		//课程分类(一级分类）
//		List<ConstsClassifyVO> classifys = portalBusiness.queryAllClassify();
//
//		//课程推荐
//		portalBusiness.prepareRecomdCourses(classifys);
//		mv.addObject("classifys", classifys);
//
//
//		//获取5门实战课推荐，根据权重（weight）进行排序
//		CourseQueryDto queryEntity = new CourseQueryDto();
//		queryEntity.setCount(5);//5门
//		queryEntity.setFree(CourseEnum.FREE_NOT.value());//非免费的：实战课
//		queryEntity.descSortField("weight");//按照weight降序排列
//		List<Course> actionCourseList = this.courseService.queryList(queryEntity);
//		mv.addObject("actionCourseList", actionCourseList);
//
//		//获取5门免费课推荐，根据权重（weight）进行排序
//		queryEntity.setFree(CourseEnum.FREE.value());//非免费的：实战课
//		List<Course> freeCourseList = this.courseService.queryList(queryEntity);
//		mv.addObject("freeCourseList", freeCourseList);
//
//		//获取7门java课程，根据权重（学习数量studyCount）进行排序
//		queryEntity.setCount(7);
//		queryEntity.setFree(null);//不分实战和免费类别
//		queryEntity.setSubClassify("java");//java分类
//		queryEntity.descSortField("studyCount");//按照studyCount降序排列
//		List<Course> javaCourseList = this.courseService.queryList(queryEntity);
//		mv.addObject("javaCourseList", javaCourseList);
//
//		//加载讲师
//		List<AuthUser> recomdTeacherList = authUserService.queryRecomd();
//		mv.addObject("recomdTeacherList", recomdTeacherList);
//
//		return mv;
//	}


    /**
     * 课程章节页面 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        Long courseId = Long.valueOf(1);
        if (null == courseId)
            return new ModelAndView("error/404");

        //获取课程
        Course course = courseService.getById(courseId);
        if (null == course)
            return new ModelAndView("error/404");

        //获取课程章节
        ModelAndView mv = new ModelAndView("learn");
        List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseId);
        mv.addObject("course", course);
        mv.addObject("chaptSections", chaptSections);

        //获取讲师
        AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());
        if (null != courseTeacher && StringUtils.isNotEmpty(courseTeacher.getHeader())) {
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        mv.addObject("courseTeacher", courseTeacher);

        //获取推荐课程
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.descSortField("weight");
        queryEntity.setCount(5);//5门推荐课程
        queryEntity.setSubClassify(course.getSubClassify());
        List<Course> recomdCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("recomdCourseList", recomdCourseList);

        //当前学习的章节
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setCourseId(course.getId());
        userCourseSection.setUserId(SessionContext.getUserId());
        userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);
        if (null != userCourseSection) {
            CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
            mv.addObject("curCourseSection", curCourseSection);
        }

        //获取栏目
        Menu menu = new Menu();
        List<Menu> menus = this.menuService.queryShow(1);
        mv.addObject("menuList", menus);

        //获取当前用户
        Long userId = SessionContext.getUserId();
        boolean isSign = false;
        if (userId != null) {

            AuthUser authUser = authUserService.getById(userId);
            Sign entity = new Sign();
            entity.setStuId(authUser.getUsername());
            isSign = signService.isSign(entity);
        }
        mv.addObject("isSign", isSign);
        return mv;
    }


    @RequestMapping(value = "/redDownload")
    public ModelAndView toResDownLoad() {
        ModelAndView mv = new ModelAndView("download/list.html");
        return mv;
    }

    @RequestMapping(value = "/workUpload")
    public ModelAndView toWorkUpload() {
        ModelAndView mv = new ModelAndView("work/list.html");
        return mv;
    }


    @RequestMapping(value = "/notice")
    public ModelAndView toNotice() {
        ModelAndView mv = new ModelAndView("notice/list.html");
        return mv;
    }


}

