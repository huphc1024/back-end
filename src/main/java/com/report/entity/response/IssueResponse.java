package com.report.entity.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("issue_id")
    private Integer issueId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("project_id")
    private Integer projectId;
    
    @JsonProperty("project_name")
    private String projectName;
    
    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("finish_date")
    private String finishDate;

    @JsonProperty("done")
    private String done;

    @JsonProperty("work_type")
    private String workType;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created")
    private String created;

    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @JsonProperty("lastmodified")
    private String lastmodified;
    
    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;

    @JsonProperty("del_flg")
    private String delFlg;

    public String getProjectName() {
        return projectName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
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

    public IssueResponse(Integer issueId, Integer userId, String fullname, Integer projectId, String projectName, String name, String description, String startDate, String finishDate, String done,
            String workType, String status, String created, String createdbyUsername, String lastmodified, String lastmodifiedbyUsername, String delFlg) {
        super();
        this.issueId = issueId;
        this.userId = userId;
        this.fullname = fullname;
        this.projectId = projectId;
        this.projectName = projectName;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.done = done;
        this.workType = workType;
        this.status = status;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.delFlg = delFlg;
    }

    public IssueResponse() {
        super();
    }
}
