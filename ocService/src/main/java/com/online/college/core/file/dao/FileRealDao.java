package com.online.college.core.file.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.entity.FileReal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FileRealDao
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 23:12
 * @Version 1.0
 **/

public interface FileRealDao {


    List<FileReal> selectForPage(FileReal fileReal, TailPage<FileReal> page);

    /**
     * 获取总数量
     **/
    Integer getTotalItemsCount(FileReal fileReal);

    void save(FileReal fileReal);

    void update(FileReal fileReal);

    void delete(FileReal fileReal);


    FileReal selectById(@Param("id") Long id);

    FileReal selectByParentId(@Param("fileParentId") Long fileParentId);
}
