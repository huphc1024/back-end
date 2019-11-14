package com.report.entity.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("team_id")
    private Integer teamId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("teamlead")
    private String teamlead;
    
    @JsonProperty("active")
    private String active;

    @JsonProperty("created")
    private String created;

    @JsonProperty("createdby_username")
    private String createdbyUsername;

    @JsonProperty("lastmodified")
    private String lastmodified;

    @JsonProperty("lastmodifiedby_username")
    private String lastmodifiedbyUsername;

    @JsonProperty("in_team")
    private String inTeam;

    public String getInTeam() {
        return inTeam;
    }

    public void setInTeam(String inTeam) {
        this.inTeam = inTeam;
    }

    public String getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(String teamlead) {
        this.teamlead = teamlead;
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

    public TeamResponse() {
        // TODO Auto-generated constructor stub
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public TeamResponse(Integer teamId, String name, String teamlead, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.teamlead = teamlead;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }
    public TeamResponse(Integer teamId, String name, String teamlead, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername, String inTeam) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.teamlead = teamlead;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.inTeam = inTeam;
    }

    public TeamResponse(Integer teamId, String name, String teamlead, String active, String created, String createdbyUsername, String lastmodified,
            String lastmodifiedbyUsername, String inTeam) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.teamlead = teamlead;
        this.active = active;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
        this.lastmodified = lastmodified;
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
        this.inTeam = inTeam;
    }
    

}
