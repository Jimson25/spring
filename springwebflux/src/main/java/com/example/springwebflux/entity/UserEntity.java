package com.example.springwebflux.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private String userId;
    private String username;
    private String passwd;
    private String status;

    public UserEntity() {
    }

    public UserEntity(String userId, String username, String passwd, String status) {
        this.userId = userId;
        this.username = username;
        this.passwd = passwd;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
