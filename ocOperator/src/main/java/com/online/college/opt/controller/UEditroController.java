package com.online.college.opt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName UEditroController
 * @Description TODO
 * @Author like
 * @Data 2019/3/13 10:58
 * @Version 1.0
 **/
@Controller
public class UEditroController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/showPage")
    public ModelAndView showPage(){
        return new ModelAndView("ueditor/index");
    }

    @ResponseBody
    @RequestMapping("/ueditor/show1")
    private String showPage1() {
//        String rootPath = request.getSession().getServletContext()
//                .getRealPath("/");
        String rootPath = "src/main/resources/static";
        return new ActionEnter(request, rootPath).exec();
    }

    @ResponseBody
    @RequestMapping("jsp/controller")
    public void getUEditorConfig(HttpServletResponse response) {
        String rootPath = "src/main/resources";
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
