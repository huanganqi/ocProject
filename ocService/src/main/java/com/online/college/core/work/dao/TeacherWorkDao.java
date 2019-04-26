package com.online.college.core.work.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TeacherWorkDao
 * @Description 教师发布作业dao
 * @Author like
 * @Data 2019/3/31 14:10
 * @Version 1.0
 **/

public interface TeacherWorkDao {
    /**
     * 分页获取 作业通知
     **/
    List<TeacherHomeWork> selectTeacherWorkForPage(TeacherHomeWork teacherHomeWork, TailPage<TeacherHomeWork> page);

    /**
     *获取总数量
     **/
    Integer getTotalItemsCount(TeacherHomeWork teacherHomeWork);

    void save(TeacherHomeWork teacherHomeWork);

    void update(TeacherHomeWork teacherHomeWork);

    void delete(@Param("id") Long id);

    /**
     * 通过id查询
     *
     * @return
     */
    TeacherHomeWork selectById(@Param("id") Long id);





}
