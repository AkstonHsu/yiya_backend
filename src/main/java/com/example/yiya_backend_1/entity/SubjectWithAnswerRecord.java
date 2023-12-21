package com.example.yiya_backend_1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubjectWithAnswerRecord {
    @JsonProperty("childrenname")
    private String childrenname;
    @JsonProperty("age")
    private int age;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("languageDevelopment")
    private String languageDevelopment;
    @JsonProperty("testCnt")
    private int testCnt;
    @JsonProperty("answerRecords")
    private List<AnswerRecord> answerRecords;

    public SubjectWithAnswerRecord(String childrenname, int age, String sex, String languageDevelopment, int testCnt, List<AnswerRecord> answerRecords) {
        this.childrenname = childrenname;
        this.age = age;
        this.sex = sex;
        this.languageDevelopment = languageDevelopment;
        this.testCnt = testCnt;
        this.answerRecords = answerRecords;
    }
}
