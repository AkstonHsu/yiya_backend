package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Adrin
 */
@TableName("paper")
public class Paper {
    @TableId(type = IdType.AUTO)
    private long pid;
    private long did;
    private String title;
    private String description;
    private String source;

    private int amount;
    private String paperAudio;

    private String correctAnswer;

    private int ageLimit;

    private String doctorName;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaperAudio() {
        return paperAudio;
    }

    public void setPaperAudio(String paperAudio) {
        this.paperAudio = paperAudio;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "pid=" + pid +
                ", did=" + did +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", paperAudio='" + paperAudio + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", ageLimit=" + ageLimit +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }
}
