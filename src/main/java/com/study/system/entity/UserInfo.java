package com.study.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserInfo {
    private long id;
    private String userId;
    private String userPassword;
    private String userName;
    private String userSchool;
    private String userSex;
    private String userRole;
    private Date createDate;

    public UserInfo() {
    }

    public UserInfo(long id, String userId, String userPassword, String userName, String userSchool, String userSex, String userRole, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSchool = userSchool;
        this.userSex = userSex;
        this.userRole = userRole;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="user_info$seq")
    @SequenceGenerator(name="user_info$seq",sequenceName="user_info$seq",allocationSize=1)
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

    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_school")
    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Column(name = "user_role")
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
