package com.online.college.core.file.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.entity.FileParent;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FileParentDao
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 19:38
 * @Version 1.0
 **/

public interface FileParentDao {


    void createDirectory(FileParent fileParent);

    void updateDirectory(FileParent fileParent);

    void delDirectory(@Param("id")Long id);

    FileParent findById(@Param("id") Long id);

    List<FileParent> findAllForPage(FileParent fileParent, TailPage<FileParent> page);

    Integer getTotalItemsCount(FileParent fileParent);
}
