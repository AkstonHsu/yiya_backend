package com.example.yiya_backend_1.entity;

import java.util.Date;

public class AdminToSubject {
    private String childrenname;
    private String uname;
    private String sex;
    private Date birthday;
    private int testCount;

    public String getChildrenname() {
        return childrenname;
    }

    public void setChildrenname(String childrenname) {
        this.childrenname = childrenname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    @Override
    public String toString() {
        return "AdminToSubject{" +
                "childrenname='" + childrenname + '\'' +
                ", uname='" + uname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", testCount=" + testCount +
                '}';
    }
}
