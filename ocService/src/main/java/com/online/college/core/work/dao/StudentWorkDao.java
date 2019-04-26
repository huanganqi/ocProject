package com.online.college.core.work.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.work.entity.StudentHomeWork;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StudentWorkDao
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 14:09
 * @Version 1.0
 **/

public interface StudentWorkDao {

    /**
     * 分页获取 作业通知
     **/
    List<StudentHomeWork> selectStudentWorkForPage(StudentHomeWork studentHomeWork, TailPage<StudentHomeWork> page);

    /**
     * 获取总数量
     **/
    Integer getTotalItemsCount(StudentHomeWork studentHomeWork);

    void save(StudentHomeWork studentHomeWork);

    void update(StudentHomeWork studentHomeWork);

    void delete(StudentHomeWork studentHomeWork);

    /**
     * 通过id查询
     *
     * @return
     */
    StudentHomeWork selectById(@Param("id") Long id);

    StudentHomeWork selectByWorkIdAndUserId(@Param("workId") Long workId,@Param("stuId")String stuId );
}
