package com.online.college.core.file.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.entity.FileParent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FileParentService
 * @Description 文件目录service
 * @Author like
 * @Data 2019/4/19 20:56
 * @Version 1.0
 **/

public interface FileParentService {

    boolean save(FileParent fileParent);

    boolean delDirectory( Long id);

    FileParent findById(Long id);

    TailPage<FileParent> findAllForPage(FileParent fileParent, TailPage<FileParent> page);

}
