package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@TableName("answerrecord")
public class AnswerRecord {
    @TableId(type = IdType.AUTO)
    private Long aid;
    private Long uid;
    private Long pid;
    private String answerSheet;
    private String  testDate;
    private int correctCnt=0;
    private String title;
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

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String  testDate) {
        this.testDate = testDate;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }

    public void setCorrectCnt(int correctCnt) {
        this.correctCnt = correctCnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
