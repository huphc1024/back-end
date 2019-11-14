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
@Table(name = "dr_task")
@NamedQuery(name = "Report.findAll", query = "SELECT t FROM Task t")
public class Task implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    @JsonProperty("task_id")
    private Integer taskId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "team_id")
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "content")
    @JsonProperty("content")
    private String content;

    @Column(name = "start_date")
    @JsonProperty("start_date")
    private String startDate;

    @Column(name = "finish_date")
    @JsonProperty("finish_date")
    private String finishDate;

    @Column(name = "done")
    @JsonProperty("done")
    private String done;

    @Column(name = "work_type")
    @JsonProperty("work_type")
    private String workType;

    @Column(name = "unit")
    @JsonProperty("unit")
    private String unit;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

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

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

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

    public Task() {
        super();
        // TODO Auto-generated constructor stub
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

    public Task(Integer taskId, Integer userId, String name, String description, String content, String startDate, String finishDate, String created,
            String createdbyUsername, String lastmodified) {
        super();
        this.taskId = taskId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.content = content;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
    }

}
