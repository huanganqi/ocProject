package com.online.college.core.work.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TeacherWorkService
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 14:41
 * @Version 1.0
 **/

public interface TeacherWorkService {

    TailPage<TeacherHomeWork> selectTeacherWorkForPage(TeacherHomeWork teacherHomeWork, TailPage<TeacherHomeWork> page);

    boolean save(TeacherHomeWork teacherHomeWork);

    void update(TeacherHomeWork teacherHomeWork);

    boolean delete(Long id);

    /**
     * 通过id查询
     *
     * @return
     */
    TeacherHomeWork selectById(@Param("id") Long id);
}
