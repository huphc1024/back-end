package com.report.service.impl;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.IssueDao;
import com.report.dao.UserDao;
import com.report.entity.ResultBean;
import com.report.entity.request.IssueSearchListRequest;
import com.report.entity.Issue;
import com.report.entity.Project;
import com.report.entity.response.IssueResponse;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.IssuePageResponse;
import com.report.service.IssueService;
import com.report.service.ProjectService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;
import com.report.utils.SendMail;

@Service
@Transactional(rollbackOn = Exception.class)
public class IssueServiceImpl extends AbstractBaseDao implements IssueService {

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private ProjectService projectService;

    @Autowired
    public SendMail sendMail;

    @Autowired
    public UserDao userDao;

    private void getTaskEntity(String json, Issue entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "project_id")) {
            entity.setProjectId(DataUtil.getJsonInteger(jsonObject, "project_id"));
        }
        if (DataUtil.hasMember(jsonObject, "user_id")) {
            entity.setUserId(DataUtil.getJsonInteger(jsonObject, "user_id"));
        }
        if (DataUtil.hasMember(jsonObject, "name")) {
            entity.setName(DataUtil.getJsonString(jsonObject, "name"));
        }
        if (DataUtil.hasMember(jsonObject, "description")) {
            entity.setDescription(DataUtil.getJsonString(jsonObject, "description"));
        }
        if (DataUtil.hasMember(jsonObject, "start_date")) {
            entity.setStartDate(DataUtil.getJsonString(jsonObject, "start_date"));
        }
        if (DataUtil.hasMember(jsonObject, "finish_date")) {
            entity.setFinishDate(DataUtil.getJsonString(jsonObject, "finish_date"));
        }
        if (DataUtil.hasMember(jsonObject, "done")) {
            entity.setDone(DataUtil.getJsonString(jsonObject, "done"));
        }
        if (DataUtil.hasMember(jsonObject, "work_type")) {
            entity.setWorkType(DataUtil.getJsonString(jsonObject, "work_type"));
        }
        if (DataUtil.hasMember(jsonObject, "status")) {
            entity.setStatus(DataUtil.getJsonString(jsonObject, "status"));
        }
    }

    @Override
    public ResultBean getIssueById(Integer issueId) throws Exception {
        Issue report = issueDao.getIssueById(issueId);
        return new ResultBean(report, "200", "OK");
    }

    @Override
    public ResultBean add(HttpServletRequest request, String json) throws Exception {
        Issue entity = new Issue();
        entity.setCreated(DataUtil.getLocalDateTime());
        entity.setDelFlg(Constants.DEL_FLG_0);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        this.getTaskEntity(json, entity);
        issueDao.add(entity);
        sendMail.sendMailAddTask(userDao.getUser(entity.getUserId()), entity.getName(), entity.getCreatedbyUsername());
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean update(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer issueId = DataUtil.getJsonInteger(jsonObject, "issue_id");
        Issue entity = issueDao.getIssueById(issueId);
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setLastmodifiedbyUsername(email);
        if (delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
        } else {
            this.getTaskEntity(json, entity);
        }
        issueDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean getIssuesByProject(Integer projectId, String name, String date, Integer page, Integer size) {
        IssueSearchListRequest searchListRequest = new IssueSearchListRequest();
        IssuePageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("projectId", projectId, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("issueId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "dd/MM/yyyy"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (IssuePageResponse) issueDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getIssuesByUser(Integer user_id, String name, String date, Integer page, Integer size) {
        IssueSearchListRequest searchListRequest = new IssueSearchListRequest();
        IssuePageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("userId", user_id, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("issueId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "dd/MM/yyyy"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (IssuePageResponse) issueDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResultBean getIssues(HttpServletRequest request, Integer project_id, String name, String date, String status, String work_type, Integer page,
            Integer size) {
        IssueSearchListRequest searchListRequest = new IssueSearchListRequest();
        IssuePageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        if (name.equals("null")) {
            name = null;
        }
        if (status.equals("null")) {
            status = null;
        }
        if (work_type.equals("null")) {
            work_type = null;
        }
        if (date.equals("null")) {
            date = null;
        }
        if (project_id == 0 || project_id.toString() == "null") {
            project_id = null;
        }
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "dd/MM/yyyy"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("projectId", project_id, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("status", status, true);
        searchListRequest.addSearchField("workType", work_type, true);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addSearchField("pdelFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addSearchField("udelFlg", Constants.DEL_FLG_0, true);
        searchListRequest.addsSortField("issueId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "dd/MM/yyyy"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        }
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        List<UserRoleResponse> roles = DataUtil.getUserInfoByToken(request).getRoles();
        Integer countRole = 0;
        Integer countRoleManager = 0;
        for (UserRoleResponse userRoleResponse : roles) {
            if (userRoleResponse.getRole().equals("ADMIN")) {
                countRole++;
            } else if (userRoleResponse.getRole().equals("MANAGER")) {
                countRoleManager++;
            }
        }
        if (countRole == 0) { // khong la admin thi get ra theo id_user do'
            if (countRoleManager > 0) {
                searchListRequest.addSearchField("userIdM", DataUtil.getUserIdByToken(request), true);
            } else {
                searchListRequest.addSearchField("userId", DataUtil.getUserIdByToken(request), true);
            }
        }
        if (countRoleManager > 0) { // thang nay la manager
            if (project_id == null || project_id == 0) {
                List<Project> listProjects = (List<Project>) projectService.getProjects(request).getData();
                for (Project p : listProjects) {
                    searchListRequest.addSearchField("m" + p.getProjectId().toString(), p.getProjectId(), true);
                }
            }
        }
        response = (IssuePageResponse) issueDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getIssueDetail(Integer issueId) throws Exception {
        IssueResponse report = issueDao.getDetailIssue(issueId);
        return new ResultBean(report, "200", "OK");
    }

}
