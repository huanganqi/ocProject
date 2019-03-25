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

    Menu getById(@Param("id") Long id);

    /**
     * 查询展示的menu
     * @param isShow
     * @return
     */
    List<Menu> querySelective(@Param("isShow") Integer isShow);

    /**
     * 查询全部menu
     * @return
     */
    List<Menu> queryAll();

    /**
     * 修改
     * @param menu
     */
    void update(Menu menu);

    /**
     *
     * @param menu
     */
    void updateSelectivity(Menu menu);
}
