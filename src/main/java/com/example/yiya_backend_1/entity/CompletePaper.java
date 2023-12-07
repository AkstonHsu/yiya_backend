package com.example.yiya_backend_1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CompletePaper implements Serializable {

    private long pid;

    private String title;

    private String description;
    private String source;
    private String paperAudio;
    private int amount;
    @JsonProperty("questions")
    private List<CompleteQuestion> questions;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
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

    public String getPaperAudio() {
        return paperAudio;
    }

    public void setPaperAudio(String paperAudio) {
        this.paperAudio = paperAudio;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<CompleteQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CompleteQuestion> questions) {
        this.questions = questions;
    }

    // Getters and Setters

    public CompletePaper(long pid, String title, String description, String source,  String paperAudio,int amount, List<CompleteQuestion> questions) {
        this.pid = pid;
        this.title = title;
        this.description = description;
        this.source = source;
        this.paperAudio=paperAudio;
        this.amount = amount;
        this.questions = questions;
    }
}
