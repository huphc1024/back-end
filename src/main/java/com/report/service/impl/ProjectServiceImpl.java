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
import com.report.dao.ProjectDao;
import com.report.dao.UserDao;
import com.report.entity.ResultBean;
import com.report.entity.Project;
import com.report.entity.ProjectUser;
import com.report.entity.User;
import com.report.entity.request.ProjectSearchListRequest;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.ProjectPageResponse;
import com.report.service.ProjectService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;
import com.report.utils.SendMail;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjectServiceImpl extends AbstractBaseDao implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public SendMail sendMail;

    @Override
    public ResultBean getProjectById(Integer projectId) {
        Project entity = projectDao.getProjectById(projectId);
        if (Objects.isNull(entity)) {
        }
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean addProject(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Project entity = new Project();
        entity.setCreated(DataUtil.getLocalDateTime());
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        entity.setDelFlg(Constants.DEL_FLG_0);
        this.getProjectEntity(json, entity);
        projectDao.add(entity);
        List<User> users = new ArrayList<User>();
        JsonArray arrUser = DataUtil.getJsonArrayWithMember(jsonObject, "users");
        for (int i = 0; i < arrUser.size(); i++) {
            JsonObject jb = arrUser.get(i).getAsJsonObject();
            ProjectUser projectUser = new ProjectUser();
            projectUser.setProjectId(entity.getProjectId());
            projectUser.setCreated(DataUtil.getLocalDateTime());
            projectUser.setDelFlg(Constants.DEL_FLG_0);
            projectUser.setCreatedbyUsername(email);
            projectUser.setUserId(DataUtil.getJsonInteger(jb, "user_id"));
            users.add(userDao.getUser(DataUtil.getJsonInteger(jb, "user_id")));
            projectDao.addProjectUser(projectUser);
        }
        sendMail.sendMailAddProject(users, entity.getName());
        List<User> managers = new ArrayList<User>();
        JsonArray arrManagers = DataUtil.getJsonArrayWithMember(jsonObject, "managers");
        for (int i = 0; i < arrManagers.size(); i++) {
            JsonObject jb = arrManagers.get(i).getAsJsonObject();
            ProjectUser projectUser = new ProjectUser();
            projectUser.setProjectId(entity.getProjectId());
            projectUser.setCreated(DataUtil.getLocalDateTime());
            projectUser.setDelFlg(Constants.DEL_FLG_0);
            projectUser.setCreatedbyUsername(email);
            projectUser.setUserId(DataUtil.getJsonInteger(jb, "user_id"));
            managers.add(userDao.getUser(DataUtil.getJsonInteger(jb, "user_id")));
            projectDao.addProjectUser(projectUser);
            sendMail.sendMailAddProject(users, entity.getName());
        }
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean updateProject(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer projectId = DataUtil.getJsonInteger(jsonObject, "project_id");
        Project entity = projectDao.getProjectById(projectId);
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
        List<Integer> listUserOld = userDao.getUsersOldByProject(projectId);
        List<Integer> listManOld = userDao.getManOldByProject(projectId);
        List<User> usersSendMail = new ArrayList<User>();
        if (delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
            for (Integer i : listUserOld) {
                projectDao.delProjectUser(i, projectId, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
            }
        } else {
            if (active.equals("1")) {
                entity.setActive(Constants.DEL_FLG_1);
            } else {
                List<Integer> listUserNew = new ArrayList<Integer>();
                List<Integer> listUserNew2 = new ArrayList<Integer>();
                List<Integer> listManNew = new ArrayList<Integer>();
                List<Integer> listManNew2 = new ArrayList<Integer>();
                JsonArray arrUser = DataUtil.getJsonArrayWithMember(jsonObject, "users");
                JsonArray arrMan = DataUtil.getJsonArrayWithMember(jsonObject, "managers");
                for (int i = 0; i < arrUser.size(); i++) {
                    JsonObject jb = arrUser.get(i).getAsJsonObject();
                    listUserNew.add(DataUtil.getJsonInteger(jb, "user_id"));
                    listUserNew2.add(DataUtil.getJsonInteger(jb, "user_id"));
                }
                for (int i = 0; i < arrMan.size(); i++) {
                    JsonObject jb = arrMan.get(i).getAsJsonObject();
                    listManNew.add(DataUtil.getJsonInteger(jb, "user_id"));
                    listManNew2.add(DataUtil.getJsonInteger(jb, "user_id"));
                }
                // lấy những user mới vào Project
                listUserNew.removeAll(listUserOld);
                //lấy user out Project
                listUserOld.removeAll(listUserNew2);
                for (Integer i : listUserNew) {
                    ProjectUser projectUser = new ProjectUser();
                    projectUser.setProjectId(entity.getProjectId());
                    projectUser.setUserId(i);
                    projectUser.setCreated(DataUtil.getLocalDateTime());
                    projectUser.setCreatedbyUsername(DataUtil.getUserNameByToken(request));
                    projectUser.setDelFlg(Constants.DEL_FLG_0);
                    usersSendMail.add(userDao.getUser(i));
                    projectDao.addProjectUser(projectUser);
                }
                for (Integer i : listUserOld) {
                    projectDao.updateProjectUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
                }
                // lấy những manager mới vào Project
                listManNew.removeAll(listManOld);
                //lấy manager out Project
                listManOld.removeAll(listManNew2);
                for (Integer i : listManNew) {
                    ProjectUser projectUser = new ProjectUser();
                    projectUser.setProjectId(entity.getProjectId());
                    projectUser.setUserId(i);
                    projectUser.setCreated(DataUtil.getLocalDateTime());
                    projectUser.setCreatedbyUsername(DataUtil.getUserNameByToken(request));
                    projectUser.setDelFlg(Constants.DEL_FLG_0);
                    usersSendMail.add(userDao.getUser(i));
                    projectDao.addProjectUser(projectUser);
                }
                for (Integer i : listManOld) {
                    projectDao.updateProjectUser(i, DataUtil.getLocalDateTime(), DataUtil.getUserNameByToken(request));
                }
                this.getProjectEntity(json, entity);
            }
        }
        projectDao.update(entity);
        if (!DataUtil.isEmpty(usersSendMail)) {
            sendMail.sendMailAddProject(usersSendMail, entity.getName());
        }
        return new ResultBean(entity, "200", "OK");
    }

    private void getProjectEntity(String json, Project entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "name")) {
            entity.setName(DataUtil.getJsonString(jsonObject, "name"));
        }
        if (DataUtil.hasMember(jsonObject, "description")) {
            entity.setDescription(DataUtil.getJsonString(jsonObject, "description"));
        }
    }

    @Override
    public ResultBean getProjects(HttpServletRequest request, String name, String date, Integer page, Integer size) {
        ProjectSearchListRequest searchListRequest = new ProjectSearchListRequest();
        ProjectPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        if(name.equals("null")) {
            name = null;
        }
        if(date.equals("null")) {
            date = null;
        }
        searchListRequest.addSearchField("name", name, false);
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "dd/MM/yyyy"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addSearchField("tuDelFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addsSortField("projectId DESC");
        List<UserRoleResponse> roles = DataUtil.getUserInfoByToken(request).getRoles();
        Integer countRole = 0;
        for (UserRoleResponse userRoleResponse : roles) {
            if (userRoleResponse.getRole().equals("ADMIN")) {
                countRole++;
            }
        }
        if (countRole == 0) { // khong la admin thi get ra theo id_user do'
            searchListRequest.addSearchField("userId", DataUtil.getUserIdByToken(request), true);
        }
        response = (ProjectPageResponse) projectDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getProjects(HttpServletRequest request) {
        List<UserRoleResponse> roles = DataUtil.getUserInfoByToken(request).getRoles();
        Boolean isAdmin = true;
        Integer countRole = 0;
        for (UserRoleResponse userRoleResponse : roles) {
            if (userRoleResponse.getRole().equals("ADMIN")) {
                countRole++;
            }
        }
        if (countRole == 0) { // khong la admin thi get ra theo id_user do'
            isAdmin = false;
        }
        List<Project> entity = projectDao.getProjects(isAdmin, DataUtil.getUserIdByToken(request));
        return new ResultBean(entity, "200", "OK");
    }

}
