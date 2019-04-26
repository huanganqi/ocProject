package com.online.college.core.file.entity;

import com.online.college.common.orm.BaseEntity;

/**
 * @ClassName FileReal
 * @Description 文件
 * @Author like
 * @Data 2019/4/19 19:35
 * @Version 1.0
 **/

public class FileReal extends BaseEntity {

    private String fileName;

    private Long fileParentId;

    private String fileSize;

    private String fileType;

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileParentId() {
        return fileParentId;
    }

    public void setFileParentId(Long fileParentId) {
        this.fileParentId = fileParentId;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
