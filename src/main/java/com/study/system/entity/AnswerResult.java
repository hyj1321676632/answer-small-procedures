package com.study.system.entity;

import lombok.Data;

@Data
public class AnswerResult {
    private long titleId;
    private String titleHeader;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String titleAnswer;
    private String parseText;
    private String answer;
    private String result;
}
