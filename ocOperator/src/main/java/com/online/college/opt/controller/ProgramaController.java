package com.online.college.opt.controller;

import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.menu.domain.Menu;
import com.online.college.core.menu.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ProgramaController
 * @Description 栏目管理Controller
 * @Author like
 * @Data 2019/3/17 19:31
 * @Version 1.0
 **/
@Controller
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    private MenuServiceImpl menuService;

    @RequestMapping(value = "/queryPrograma", method = RequestMethod.GET)
    public ModelAndView queryPrograma() {
        ModelAndView mv = new ModelAndView("cms/programa/pagelist");
        mv.addObject("curNav", "programManage");
        List<Menu> menus = menuService.queryAll();
        mv.addObject("queryList", menus);
        return mv;
    }

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        Menu menu = menuService.getById(id);
        return JsonView.render(menu);
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(Menu entity){
        entity.setMenuName(null);//不更新
        menuService.update(entity);
        return new JsonView(0).toString();
    }

}
