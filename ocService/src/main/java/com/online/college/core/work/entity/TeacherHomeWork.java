package com.online.college.core.work.entity;

import com.online.college.common.orm.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Work
 * @Description 作业
 * @Author like
 * @Data 2019/3/31 13:41
 * @Version 1.0
 **/

public class TeacherHomeWork extends BaseEntity {

    /**
     * 作业标题
     */
    private String title;

    /**
     * 作业要求
     */
    private String require;

    /**
     * 完成时间
     */
    private Date finishTime;

    private Integer status;

    private List<StudentHomeWork> studentHomeWorkList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<StudentHomeWork> getStudentHomeWorkList() {
        return studentHomeWorkList;
    }

    public void setStudentHomeWorkList(List<StudentHomeWork> studentHomeWorkList) {
        this.studentHomeWorkList = studentHomeWorkList;
    }


}
