package com.online.college.core.notice.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.notice.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName NoticeDao
 * @Description 通知公告 dao层
 * @Author like
 * @Data 2019/3/20 10:43
 * @Version 1.0
 **/

public interface NoticeDao {

    /**
     * 保存
     *
     * @param notice
     */
    void save(Notice notice);

    /**
     * 修改
     *
     * @param notice
     */
    void update(Notice notice);

    /**
     * 查询全部
     *
     * @return
     */
    List<Notice> queryAll();

    /**
     *获取总数量
     **/
    Integer getTotalItemsCount(Notice notice);

    /**
     *分页获取
     **/
    public List<Notice> queryPage(Notice notice , TailPage<Notice> page);

    /**
     * 通过id查询
     *
     * @return
     */
    Notice queryById(@Param("id") Long id);

    /**
     * 通过id删除
     * @param id
     */
    void delete(Long id);
}
