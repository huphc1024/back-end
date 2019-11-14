package com.report.service;

import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface TeamService {

    public ResultBean getTeamById(Integer teamId);

    public ResultBean addTeam(HttpServletRequest request, String json) throws Exception;

    public ResultBean updateTeam(HttpServletRequest request, String json) throws Exception;

    public ResultBean getTeams(HttpServletRequest request, String name, String date, Integer page, Integer size);

    public ResultBean getTeamsById(Integer user_id, String name, String date, Integer page, Integer size);
}
