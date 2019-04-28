package com.online.college.opt.controller;

import com.online.college.common.page.TailPage;
import com.online.college.core.sign.entity.Sign;
import com.online.college.core.sign.service.SignService;
import com.online.college.core.work.entity.StudentHomeWork;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName SignController
 * @Description TODO
 * @Author like
 * @Data 2019/4/27 21:59
 * @Version 1.0
 **/
@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Sign sign, TailPage<Sign> page) {
        ModelAndView mv = new ModelAndView("cms/sign/list");
        page = signService.selectSignForPage(sign, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", sign);
        return mv;
    }
}
