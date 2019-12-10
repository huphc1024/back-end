package com.report.entity.response;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagerResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "fullname")
    @JsonProperty("fullname")
    private String fullname;
    
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ManagerResponse(Integer userId, String fullname, String email) {
        super();
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
    }

    public ManagerResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    
}
