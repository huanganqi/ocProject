package com.online.college.core.file.entity;

import com.online.college.common.orm.BaseEntity;

import java.util.List;

/**
 * @ClassName FileParent
 * @Description 文件父目录
 * @Author like
 * @Data 2019/4/19 19:33
 * @Version 1.0
 **/

public class FileParent extends BaseEntity {

    private String directoryName;

    private List<FileReal>   fileRealList;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public List<FileReal> getFileRealList() {
        return fileRealList;
    }

    public void setFileRealList(List<FileReal> fileRealList) {
        this.fileRealList = fileRealList;
    }
}
