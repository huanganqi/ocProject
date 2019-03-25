package com.online.college.portal.controller;

import com.online.college.common.web.JsonView;
import com.online.college.core.menu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MenuController
 * @Description 自定义菜单栏目
 * @Author like
 * @Data 2019/3/22 23:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/list")
    public String getMenu() {
        return JsonView.render(menuService.queryShow(1));
    }
}
