package com.online.college.core.work.entity;

import com.online.college.common.orm.BaseEntity;

/**
 * @ClassName HomeWorkStudent
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 13:44
 * @Version 1.0
 **/

public class StudentHomeWork  extends BaseEntity {

    /**
     * 作业具体内容
     */
    private String content;

    /**
     * 学生id
     */
    private String stuId;

    private Long workId;

    private Double score;

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
