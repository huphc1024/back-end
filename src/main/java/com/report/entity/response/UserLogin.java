package com.report.entity.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogin implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // User ID
    @JsonProperty("user_id")
    private Integer userId;

    // Username
    @JsonProperty("email")
    private String email;

    // Password
    @JsonProperty("password")
    @JsonIgnore
    private String password;

    private List<UserRoleResponse> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleResponse> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleResponse> roles) {
        this.roles = roles;
    }

    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserLogin(Integer userId, String email, String password, List<UserRoleResponse> roles) {
        super();
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
