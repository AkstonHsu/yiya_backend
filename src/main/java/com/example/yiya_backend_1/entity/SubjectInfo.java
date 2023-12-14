package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import java.time.LocalDate;
@TableName("subjectinfo")
public class SubjectInfo {
    @TableId(type = IdType.AUTO)
    private long id;

    private long uid;
    private String childrenname;
    private String sex;
    private LocalDate birthday;
    private String healthy;
    private String languageDevelopment;
    private String dialect;
    private String academicRecord;
    private String residence;
    private String urbanOrRural;
    private int sibling;
    private String contacts;
    private String accompany;
    private String side;
    private LocalDate openTime;
    private String cochlearBrand;
    private int age;

    private int testCoount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getChildrenname() {
        return childrenname;
    }

    public void setChildrenname(String childrenname) {
        this.childrenname = childrenname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getLanguageDevelopment() {
        return languageDevelopment;
    }

    public void setLanguageDevelopment(String languageDevelopment) {
        this.languageDevelopment = languageDevelopment;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getAcademicRecord() {
        return academicRecord;
    }

    public void setAcademicRecord(String academicRecord) {
        this.academicRecord = academicRecord;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getUrbanOrRural() {
        return urbanOrRural;
    }

    public void setUrbanOrRural(String urbanOrRural) {
        this.urbanOrRural = urbanOrRural;
    }

    public int getSibling() {
        return sibling;
    }

    public void setSibling(int sibling) {
        this.sibling = sibling;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAccompany() {
        return accompany;
    }

    public void setAccompany(String accompany) {
        this.accompany = accompany;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public LocalDate getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDate openTime) {
        this.openTime = openTime;
    }

    public String getCochlearBrand() {
        return cochlearBrand;
    }

    public void setCochlearBrand(String cochlearBrand) {
        this.cochlearBrand = cochlearBrand;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTestCoount() {
        return testCoount;
    }

    public void setTestCoount(int testCoount) {
        this.testCoount = testCoount;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", childrenname='" + childrenname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", healthy='" + healthy + '\'' +
                ", languageDevelopment='" + languageDevelopment + '\'' +
                ", dialect='" + dialect + '\'' +
                ", academicRecord='" + academicRecord + '\'' +
                ", residence='" + residence + '\'' +
                ", urbanOrRural='" + urbanOrRural + '\'' +
                ", sibling=" + sibling +
                ", contacts='" + contacts + '\'' +
                ", accompany='" + accompany + '\'' +
                ", side='" + side + '\'' +
                ", openTime=" + openTime +
                ", cochlearBrand='" + cochlearBrand + '\'' +
                '}';
    }
}
