package com.example.yiya_backend_1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompleteQuestion {
    @JsonProperty("qid")
    private long qid;
    @JsonProperty("questionType")
    private int questionType;
    @JsonProperty("questionTitle")
    private String questionTitle;
    @JsonProperty("questionAudio")
    private String questionAudio;
    @JsonProperty("order")
    private int order;
    @JsonProperty("correctAnswer")
    private String correctAnswer;
    @JsonProperty("options")
    private List<Option> options;

    // Getters and Setters

    public CompleteQuestion(long qid, int questionType, String questionTitle, String questionAudio,String correctAnswer, int order, List<Option> options) {
        this.qid = qid;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.questionAudio = questionAudio;
        this.correctAnswer=correctAnswer;
        this.order = order;
        this.options = options;
    }
}
