package com.study.system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="choose_result")
public class ChooseResult implements Serializable {

    private long id;
    private long titleId;
    private String userId;
    private String chooseAnswer;

    public ChooseResult() {
    }

    public ChooseResult(long id, long titleId, String userId, String chooseAnswer) {
        this.id = id;
        this.titleId = titleId;
        this.userId = userId;
        this.chooseAnswer = chooseAnswer;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="choose_result$seq")
    @SequenceGenerator(name="choose_result$seq",sequenceName="choose_result$seq",allocationSize=1)
    @Column(name="id", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title_id")
    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "choose_answer")
    public String getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(String chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }
}
