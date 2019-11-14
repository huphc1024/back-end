package com.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.TeamDao;
import com.report.dao.UserDao;
import com.report.entity.ResultBean;
import com.report.entity.Team;
import com.report.entity.TeamUser;
import com.report.entity.User;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.TeamPageResponse;
import com.report.entity.response.search.TeamSearchListRequest;
import com.report.service.TeamService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;
import com.report.utils.SendMail;

@Service
@Transactional(rollbackOn = Exception.class)
public class TeamServiceImpl extends AbstractBaseDao implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public SendMail sendMail;

    @Override
    public ResultBean getTeamById(Integer teamId) {
        Team report = teamDao.getTeamById(teamId);
        if (Objects.isNull(report)) {
        }
        return new ResultBean(report, "200", "OK");
    }

    @Override
    public ResultBean addTeam(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Team entity = new Team();
        entity.setCreated(DataUtil.getLocalDateTime());
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        entity.setDelFlg(Constants.DEL_FLG_0);
        this.getTeamEntity(json, entity);
        teamDao.add(entity);
        List<User> users = new ArrayList<User>();
        JsonArray arrUser = DataUtil.getJsonArrayWithMember(jsonObject, "users");
        for (int i = 0; i < arrUser.size(); i++) {
            JsonObject jb = arrUser.get(i).getAsJsonObject();
            TeamUser teamUser = new TeamUser();
            teamUser.setTeamId(entity.getTeamId());
            teamUser.setCreated(DataUtil.getLocalDateTime());
            teamUser.setDelFlg(Constants.DEL_FLG_0);
            teamUser.setRemoved(Constants.DEL_FLG_0);
            teamUser.setCreatedbyUsername(email);
            teamUser.setUserId(DataUtil.getJsonInteger(jb, "user_id"));
            users.add(userDao.getUser(DataUtil.getJsonInteger(jb, "user_id")));
            teamDao.addTeamUser(teamUser);
        }
        TeamUser leader = new TeamUser();
        leader.setTeamId(entity.getTeamId());
        leader.setCreated(DataUtil.getLocalDateTime());
        leader.setDelFlg(Constants.DEL_FLG_0);
        leader.setRemoved(Constants.DEL_FLG_0);
        leader.setUserId(DataUtil.getJsonInteger(jsonObject, "teamlead"));
        leader.setCreatedbyUsername(email);
        teamDao.addTeamUser(leader);
        sendMail.sendMailAddTeam(users, entity.getName(), userDao.getUser(DataUtil.getJsonInteger(jsonObject, "teamlead")).getFullname());
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean updateTeam(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer teamId = DataUtil.getJsonInteger(jsonObject, "team_id");
        Team entity = teamDao.getTeamById(teamId);
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        String active = null;
        if (DataUtil.hasMember(jsonObject, "active")) {
            active = DataUtil.getJsonString(jsonObject, "active");
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        entity.setLastmodifiedbyUsername(DataUtil.getUserNameByToken(request));
        List<Integer> listUserOld = userDao.getListUserOldTeam(teamId);
        List<User> usersSendMail = new ArrayList<User>();
        if (delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
            for (Integer i : listUserOld) {
                teamDao.delTeamUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
            }
        } else {
            if (active.equals("1")) {
                List<Integer> listUserNew = new ArrayList<Integer>();
                List<Integer> listUserNew2 = new ArrayList<Integer>();
                JsonArray arrUser = DataUtil.getJsonArrayWithMember(jsonObject, "users");
                for (int i = 0; i < arrUser.size(); i++) {
                    JsonObject jb = arrUser.get(i).getAsJsonObject();
                    listUserNew.add(DataUtil.getJsonInteger(jb, "user_id"));
                    listUserNew2.add(DataUtil.getJsonInteger(jb, "user_id"));
                }
                // lấy những user mới vào team
                listUserNew.removeAll(listUserOld);
                //lấy user out team
                listUserOld.removeAll(listUserNew2);
                for (Integer i : listUserNew) {
                    TeamUser teamUser = new TeamUser();
                    teamUser.setTeamId(entity.getTeamId());
                    teamUser.setUserId(i);
                    teamUser.setCreated(DataUtil.getLocalDateTime());
                    teamUser.setCreatedbyUsername(DataUtil.getUserNameByToken(request));
                    teamUser.setDelFlg(Constants.DEL_FLG_0);
                    teamUser.setRemoved(Constants.DEL_FLG_0);
                    usersSendMail.add(userDao.getUser(i));
                    teamDao.updateTeamUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
                }
                for (Integer i : listUserOld) {
                    teamDao.updateTeamUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
                }
            } else {
                List<Integer> listUserNew = new ArrayList<Integer>();
                List<Integer> listUserNew2 = new ArrayList<Integer>();
                JsonArray arrUser = DataUtil.getJsonArrayWithMember(jsonObject, "users");
                for (int i = 0; i < arrUser.size(); i++) {
                    JsonObject jb = arrUser.get(i).getAsJsonObject();
                    listUserNew.add(DataUtil.getJsonInteger(jb, "user_id"));
                    listUserNew2.add(DataUtil.getJsonInteger(jb, "user_id"));
                }
                // lấy những user mới vào team
                listUserNew.removeAll(listUserOld);
                //lấy user out team
                listUserOld.removeAll(listUserNew2);
                for (Integer i : listUserNew) {
                    TeamUser teamUser = new TeamUser();
                    teamUser.setTeamId(entity.getTeamId());
                    teamUser.setUserId(i);
                    teamUser.setCreated(DataUtil.getLocalDateTime());
                    teamUser.setCreatedbyUsername(DataUtil.getUserNameByToken(request));
                    teamUser.setDelFlg(Constants.DEL_FLG_0);
                    teamUser.setRemoved(Constants.DEL_FLG_0);
                    usersSendMail.add(userDao.getUser(i));
                    teamDao.addTeamUser(teamUser);
                }
                for (Integer i : listUserOld) {
                    teamDao.updateTeamUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
                }
                
            }
            TeamUser leader = teamDao.getLeaderOld(teamId);
            if (leader.getUserId() != DataUtil.getJsonInteger(jsonObject, "teamlead")) {
                leader.setLastmodified(DataUtil.getLocalDateTime());
                leader.setUserId(DataUtil.getJsonInteger(jsonObject, "teamlead"));
                leader.setLastmodifiedbyUsername(DataUtil.getUserNameByToken(request));
                teamDao.updateTeamUser(leader);
            }
            this.getTeamEntity(json, entity);
        }
        teamDao.update(entity);
        if (!DataUtil.isEmpty(usersSendMail)) {
            sendMail.sendMailAddTeam(usersSendMail, entity.getName(), userDao.getUser(DataUtil.getJsonInteger(jsonObject, "teamlead")).getFullname());
        }
        return new ResultBean(entity, "200", "OK");
    }

    private void getTeamEntity(String json, Team entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "name")) {
            entity.setName(DataUtil.getJsonString(jsonObject, "name"));
        }
    }

    @Override
    public ResultBean getTeams(HttpServletRequest request, String name, String date, Integer page, Integer size) {
        TeamSearchListRequest searchListRequest = new TeamSearchListRequest();
        TeamPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addsSortField("teamId DESC");
        List<UserRoleResponse> roles = DataUtil.getUserInfoByToken(request).getRoles();
        Integer countRole = 0;
        for (UserRoleResponse userRoleResponse : roles) {
            if (userRoleResponse.getRole().equals("ADMIN")) {
                countRole++;
            }
        }
        if (countRole == 0) { // khong la admin thi get ra theo id_user do'
            searchListRequest.addSearchField("userId", DataUtil.getUserIdByToken(request), true);
        } else {
            searchListRequest.addSearchField("role", "LEADER", true);
        }
        response = (TeamPageResponse) teamDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getTeamsById(Integer user_id, String name, String date, Integer page, Integer size) {
        TeamSearchListRequest searchListRequest = new TeamSearchListRequest();
        TeamPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addSearchField("userId", user_id, true);
        searchListRequest.addSearchField("role", "LEADER", true);
        response = (TeamPageResponse) teamDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

}
