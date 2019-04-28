package com.online.college.core.sign.entity;

import com.online.college.common.orm.BaseEntity;

import java.util.Date;

/**
 * @ClassName Sign
 * @Description TODO
 * @Author like
 * @Data 2019/4/27 19:06
 * @Version 1.0
 **/

public class Sign extends BaseEntity {

    private String stuId;

    private Date signInTime;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }
}
