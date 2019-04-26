package com.online.college.core.work.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.work.entity.StudentHomeWork;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName StudentWorkService
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 14:41
 * @Version 1.0
 **/

public interface StudentWorkService {

    TailPage<StudentHomeWork> selectStudentWorkForPage(StudentHomeWork studentHomeWork, TailPage<StudentHomeWork> page);

    boolean save(StudentHomeWork studentHomeWork);

    void update(StudentHomeWork studentHomeWork);

    boolean delete(StudentHomeWork studentHomeWork);

    boolean saveScore(StudentHomeWork studentHomeWork);

    /**
     * 通过id查询
     *
     * @return
     */
    StudentHomeWork selectById(@Param("id") Long id);

    StudentHomeWork selectByWorkIdAndUserId(Long workId,String stuId);
}
