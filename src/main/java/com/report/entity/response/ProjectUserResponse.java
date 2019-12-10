package com.report.entity.response;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectUserResponse {
    
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProjectUserResponse(Integer userId, String email) {
        super();
        this.userId = userId;
        this.email = email;
    }

    public ProjectUserResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    
}
