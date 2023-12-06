package com.example.yiya_backend_1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompleteQuestion {
    private long qid;
    @JsonProperty("questionType")
    private int questionType;
    @JsonProperty("questionTitle")
    private String questionTitle;
    @JsonProperty("questionAudio")
    private String questionAudio;
    @JsonProperty("order")
    private int order;
    @JsonProperty("options")
    private List<Option> options;

    // Getters and Setters

    public CompleteQuestion(long qid, int questionType, String questionTitle, String questionAudio, int order, List<Option> options) {
        this.qid = qid;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.questionAudio = questionAudio;
        this.order = order;
        this.options = options;
    }
}
