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
@Table(name = "dr_team_user")
@NamedQuery(name = "TeamUser.findAll", query = "SELECT tu FROM TeamUser tu")
public class TeamUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_user_id")
    @JsonProperty("team_user_id")
    private Integer teamUserId;

    @Column(name = "team_id")
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "created")
    @JsonProperty("created")
    private String created;

    @Column(name = "createdby_username")
    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @Column(name = "removed")
    @JsonProperty("removed")
    private String removed;

    @Column(name = "lastmodifiedby_username")
    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;

    @Column(name = "lastmodified")
    @JsonProperty("lastmodified")
    private String lastmodified;

    @Column(name = "del_flg")
    @JsonProperty("del_flg")
    private String delFlg;

    public Integer getTeamUserId() {
        return teamUserId;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public void setTeamUserId(Integer teamUserId) {
        this.teamUserId = teamUserId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public TeamUser() {
        super();
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public TeamUser(Integer teamUserId, Integer teamId, Integer userId, String created, String createdbyUsername, String removed, String lastmodifiedbyUsername,
            String lastmodified, String delFlg) {
        super();
        this.teamUserId = teamUserId;
        this.teamId = teamId;
        this.userId = userId;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.removed = removed;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.lastmodified = lastmodified;
        this.delFlg = delFlg;
    }

}
