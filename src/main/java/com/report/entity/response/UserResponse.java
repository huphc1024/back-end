package com.report.entity.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("fullname")
    private String fullname;
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("created")
    private String created;
    
    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @JsonProperty("lastmodified")
    private String lastmodified;

    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;
    
    @JsonProperty("roles")
    private String roles;

    public Integer getUserId() {
        return userId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    public UserResponse() {
        super();
    }

    public UserResponse(Integer userId, String fullname, String email, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername) {
        super();
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }
    
    public UserResponse(Integer userId, String fullname, String email, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername, String roles) {
        super();
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.roles = roles;
    }
    
    public UserResponse(Integer userId, String fullname, String email) {
        super();
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
    }
    
}
