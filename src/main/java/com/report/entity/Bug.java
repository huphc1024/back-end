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
@Table(name = "dr_bug")
@NamedQuery(name = "Bug.findAll", query = "SELECT b FROM Bug b")
public class Bug implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_id")
    @JsonProperty("bug_id")
    private Integer bugId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "task_id")
    @JsonProperty("task_id")
    private Integer taskId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "content")
    @JsonProperty("content")
    private String content;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "created")
    @JsonProperty("created")
    private String created;

    @Column(name = "createdby_username")
    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @Column(name = "lastmodifiedby_username")
    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;

    @Column(name = "lastmodified")
    @JsonProperty("lastmodified")
    private String lastmodified;

    @Column(name = "del_flg")
    @JsonProperty("del_flg")
    private String delFlg;

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

}
