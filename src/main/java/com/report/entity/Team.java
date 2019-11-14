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
@Table(name = "dr_team")
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    
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

    public String getDelFlg() {
        return delFlg;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
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

    public Team() {
        super();
    }

    public Team(Integer teamId, String name, String active, String created, String createdbyUsername, String lastmodified, String lastmodifiedbyUsername,
            String delFlg) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.active = active;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.delFlg = delFlg;
    }


}
