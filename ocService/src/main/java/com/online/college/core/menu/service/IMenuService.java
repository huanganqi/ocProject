package com.online.college.core.menu.service;

import com.online.college.core.menu.domain.Menu;

import java.util.List;

/**
 * @ClassName IMenuService
 * @Description TODO
 * @Author like
 * @Data 2019/3/6 14:20
 * @Version 1.0
 **/

public interface IMenuService {

    List<Menu> queryShow(Integer isShow);

    List<Menu> queryAll();

    Menu getById(Long id);

    void update(Menu menu);


}
