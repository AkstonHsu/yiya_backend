package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Type;
import java.util.Date;

@TableName("answerrecord")
public class AnswerRecord {
    @TableId(type = IdType.AUTO)
    private Long aid;
    private Long uid;
    private Long pid;
    private String answerSheet;
    private Date testDate;
    private int correctCnt=0;


    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(String answerSheet) {
        this.answerSheet = answerSheet;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }

    public void setCorrectCnt(int correctCnt) {
        this.correctCnt = correctCnt;
    }

    @Override
    public String toString() {
        return "AnswerRecord{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", answerSheet='" + answerSheet + '\'' +
                ", testDate=" + testDate +
                ", correctCnt=" + correctCnt +
                '}';
    }
}
