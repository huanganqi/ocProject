package com.online.college.core.menu.service.impl;

import com.online.college.core.menu.dao.MenuDao;
import com.online.college.core.menu.domain.Menu;
import com.online.college.core.menu.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MenuService
 * @Description 菜单Service
 * @Author like
 * @Data 2019/3/6 14:20
 * @Version 1.0
 **/
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    @Resource
    MenuDao menuDao;


    @Override
    public List<Menu> queryAll(Integer isShow) {
        List<Menu> menus = menuDao.queryAll(isShow);
        if (!menus.isEmpty()) {
            return menus;
        }
        return null;
    }

    @Override
    public Menu getById(Long id) {
        Menu menu = menuDao.getById(id);
        if (menu != null) {
            return menu;
        }
        return null;
    }
}
