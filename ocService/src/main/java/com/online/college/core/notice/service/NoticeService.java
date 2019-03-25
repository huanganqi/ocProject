package com.online.college.core.notice.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.notice.entity.Notice;

import java.util.List;

/**
 * @ClassName NoticeService
 * @Description 通知公告Service
 * @Author like
 * @Data 2019/3/20 13:40
 * @Version 1.0
 **/

public interface NoticeService {

    boolean save(Notice notice);

    void update(Notice notice);

    List<Notice> queryAll();

    Notice queryById(Long id);

    /**
     * 通过id删除
     *
     * @param id
     */
    boolean delete(Long id);

    /**
     *分页获取
     **/
    public TailPage<Notice> queryPage(Notice notice , TailPage<Notice> page);
}
