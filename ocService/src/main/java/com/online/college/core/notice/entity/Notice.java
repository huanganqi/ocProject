package com.online.college.core.notice.entity;

import com.online.college.common.orm.BaseEntity;

import java.util.Date;

/**
 * @ClassName Notice
 * @Description 通知公告
 * @Author like
 * @Data 2019/3/20 10:40
 * @Version 1.0
 **/

public class Notice extends BaseEntity {

    private String title;

    private String content;

    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
