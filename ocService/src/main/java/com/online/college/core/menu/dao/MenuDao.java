package com.online.college.core.menu.dao;

import com.online.college.core.menu.domain.Menu;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Description TODO
 * @Author like
 * @Data 2019/3/6 13:55
 * @Version 1.0
 **/
public interface MenuDao {

    Menu getById(Long id);

    List<Menu> queryAll(@Param("isShow") Integer isShow);

    void update(Menu menu);

    void updateSelectivity(Menu menu);
}
