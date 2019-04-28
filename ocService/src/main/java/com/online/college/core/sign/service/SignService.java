package com.online.college.core.sign.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.sign.entity.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SignService
 * @Description TODO
 * @Author like
 * @Data 2019/4/27 19:30
 * @Version 1.0
 **/

public interface SignService {

    /**
     * 通过id查询
     *
     * @return
     */
    Sign selectById( Long id);

    /**
     * 分页获取 学生签到信息
     **/
    TailPage<Sign> selectSignForPage(Sign sign, TailPage<Sign> page);

    boolean isSign(Sign sign);

    boolean save(Sign sign);

}
