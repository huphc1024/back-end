package com.report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dr_user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;
    
    @Column(name = "fullname")
    @JsonProperty("fullname")
    private String fullname;
    
    @Column(name = "password")
    @JsonProperty("password")
    private String password;
    
    @Column(name = "created")
    @JsonProperty("created")
    private String created;
    
    @Column(name = "createdby_username")
    @JsonProperty("createdby_username")
    private String createdbyUsername;
    
    @Column(name = "lastmodified")
    @JsonProperty("lastmodified")
    private String lastmodified;
    
    @Column(name = "lastmodifiedby_username")
    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;
    
    @Column(name = "del_flg")
    @JsonProperty("del_flg")
    private String delFlg;

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User() {
        super();
    }

    public User(Integer userId, String email, String fullname, String password, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername) {
        super();
        this.userId = userId;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }
    
}
