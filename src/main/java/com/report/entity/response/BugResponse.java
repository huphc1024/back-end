package com.report.entity.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BugResponse implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("bug_id")
    private Integer bugId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("task_id")
    private Integer taskId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("content")
    private String content;
    
    @JsonProperty("status")
    private String status;

    @JsonProperty("created")
    private String created;

    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;

    @JsonProperty("lastmodified")
    private String lastmodified;

    @JsonProperty("del_flg")
    private String delFlg;

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public BugResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BugResponse(Integer bugId, Integer userId, Integer taskId, String name, String content, String status, String created, String createdbyUsername,
            String lastmodifiedbyUsername, String lastmodified, String delFlg) {
        super();
        this.bugId = bugId;
        this.userId = userId;
        this.taskId = taskId;
        this.name = name;
        this.content = content;
        this.status = status;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.lastmodified = lastmodified;
        this.delFlg = delFlg;
    }
    

}
