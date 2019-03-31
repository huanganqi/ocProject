package com.online.college.opt.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.notice.entity.Notice;
import com.online.college.core.notice.service.NoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName NoticeController
 * @Description TODO
 * @Author like
 * @Data 2019/3/20 14:31
 * @Version 1.0
 **/

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/queryNotice")
    public ModelAndView queryNotice(Notice notice, TailPage<Notice> page) {
        ModelAndView mv = new ModelAndView("cms/notice/noticePageList");
        mv.addObject("curNav", "notice");

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNotice() {
        ModelAndView mv = new ModelAndView("cms/notice/add");
        mv.addObject("curNav", "notice");
        return mv;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Notice notice){
        noticeService.save(notice);
        return new JsonView(0).toString();
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public ModelAndView info(Notice notice){
        ModelAndView mv = new ModelAndView("cms/notice/update");
        Notice notice1=noticeService.queryById(notice.getId());
        mv.addObject("info", notice1);
        return mv;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Notice notice){
        noticeService.delete(notice.getId());
        return new JsonView(0).toString();
    }

}
