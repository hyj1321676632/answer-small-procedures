package com.study.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_title")
public class UserTitle {

    private long id;
    private String userId;
    private long titleId;
    private String testResult;
    private Date createDate;

    public UserTitle() {
    }

    public UserTitle(long id, String userId, long titleId, String testResult, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.titleId = titleId;
        this.testResult = testResult;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="user_title$seq")
    @SequenceGenerator(name="user_title$seq",sequenceName="user_title$seq",allocationSize=1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "title_id")
    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    @Column(name = "test_result")
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
