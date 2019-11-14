package com.report.dao;


import com.report.entity.Team;
import com.report.entity.TeamUser;
import com.report.entity.response.search.TeamPageResponse;
import com.report.entity.response.search.TeamSearchListRequest;

public interface TeamDao {

    public Team getTeamById(Integer teamId);

    public Team add(Team entity);

    public Team update(Team entity);


    public void addTeamUser(TeamUser teamUser);
    
    public void updateTeamUser(Integer userId, String string, String string2);


    TeamPageResponse findAll(TeamSearchListRequest listRequest);

    public TeamUser getLeaderOld(Integer teamId);

    public void delTeamUser(Integer i, String localDateTime, String userNameByToken);

    public void updateTeamUser(TeamUser teamUser);
}
