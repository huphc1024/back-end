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
@Table(name = "dr_user_role")
@NamedQuery(name = "UserRole.findAll", query = "SELECT ur FROM UserRole ur")
public class UserRole implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    @JsonProperty("user_role_id")
    private Integer userRoleId;

    @Column(name = "role_id")
    @JsonProperty("role_id")
    private Integer roleId;
    
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;
    
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


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public UserRole() {
        super();
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public UserRole(Integer teamUserId, Integer teamId, Integer userId, String created, String createdbyUsername, String lastmodified, String lastmodifiedbyUsername,
            String delFlg) {
        super();
        this.userRoleId = teamUserId;
        this.roleId = teamId;
        this.userId = userId;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.delFlg = delFlg;
    }

}
