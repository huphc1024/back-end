package com.report.entity.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("task_id")
    private Integer taskId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("team_id")
    private Integer teamId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    private String content;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("finish_date")
    private String finishDate;

    @JsonProperty("done")
    private String done;

    @JsonProperty("work_type")
    private String workType;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created")
    private String created;

    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @JsonProperty("lastmodified")
    private String lastmodified;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public TaskResponse(Integer taskId, Integer userId, Integer teamId, String name, String description, String content, String startDate, String finishDate,
            String done, String workType, String unit, String status, String created, String createdbyUsername, String lastmodified) {
        super();
        this.taskId = taskId;
        this.userId = userId;
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.content = content;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.done = done;
        this.workType = workType;
        this.unit = unit;
        this.status = status;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
    }

    public TaskResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

}
