package com.study.system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_title")
public class TestTitle implements Serializable {
    private long titleId;
    private String titleHeader;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String titleAnswer;
    private String parseText;

    public TestTitle() {
    }

    public TestTitle(long titleId, String titleHeader, String optionA, String optionB, String optionC, String optionD, String titleAnswer, String parseText) {
        this.titleId = titleId;
        this.titleHeader = titleHeader;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.titleAnswer = titleAnswer;
        this.parseText = parseText;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="test_title$seq")
    @SequenceGenerator(name="test_title$seq",sequenceName="test_title$seq",allocationSize=1)
    @Column(name="title_id", unique=true, nullable=false, precision=10, scale=0)
    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    @Column(name = "title_header")
    public String getTitleHeader() {
        return titleHeader;
    }

    public void setTitleHeader(String titleHeader) {
        this.titleHeader = titleHeader;
    }

    @Column(name = "option_a")
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    @Column(name = "option_b")
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    @Column(name = "option_c")
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    @Column(name = "option_d")
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    @Column(name = "title_answer")
    public String getTitleAnswer() {
        return titleAnswer;
    }

    public void setTitleAnswer(String titleAnswer) {
        this.titleAnswer = titleAnswer;
    }

    @Column(name = "parse_text")
    public String getParseText() {
        return parseText;
    }

    public void setParseText(String parseText) {
        this.parseText = parseText;
    }
}
