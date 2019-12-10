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
@Table(name = "dr_project")
@NamedQuery(name = "Project.findAll", query = "SELECT t FROM Project t")
public class Project implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @JsonProperty("project_id")
    private Integer projectId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    
    @Column(name = "description")
    @JsonProperty("description")
    private String description;
    
    @Column(name = "active")
    @JsonProperty("active")
    private String active;

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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
}
