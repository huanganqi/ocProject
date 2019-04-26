package com.online.college.core.file.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.entity.FileReal;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName FileRealService
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 23:29
 * @Version 1.0
 **/

public interface FileRealService {

    TailPage<FileReal> selectForPage(FileReal fileReal, TailPage<FileReal> page);

    boolean save(FileReal fileReal);

    void update(FileReal fileReal);

    boolean delete(FileReal fileReal);

    FileReal selectById( Long id);

    FileReal selectByParentId(Long fileParentId);
}
