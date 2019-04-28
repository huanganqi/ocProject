package com.online.college.core.sign.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.sign.entity.Sign;
import com.online.college.core.work.entity.StudentHomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SignDao
 * @Description TODO
 * @Author like
 * @Data 2019/4/27 19:10
 * @Version 1.0
 **/

public interface SignDao {

    /**
     * 通过id查询
     *
     * @return
     */
    Sign selectById(@Param("id") Long id);

    /**
     * 分页获取 学生签到信息
     **/
    List<Sign> selectSignForPage(Sign sign, TailPage<Sign> page);

    Sign selectByTime(Sign sign);

    /**
     * 获取总数量
     **/
    Integer getTotalItemsCount(Sign sign);

    void save(Sign sign);
}
