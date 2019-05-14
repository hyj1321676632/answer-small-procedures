package com.study.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_notes")
public class UserNotes implements Serializable {
    private long id;
    private String userId;
    private String content;
    private Date createDate;

    public UserNotes() {
    }

    public UserNotes(long id, String userId, String content, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="user_notes$seq")
    @SequenceGenerator(name="user_notes$seq",sequenceName="user_notes$seq",allocationSize=1)
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

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
