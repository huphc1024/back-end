package com.report.service.impl;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.TaskDao;
import com.report.dao.UserDao;
import com.report.entity.ResultBean;
import com.report.entity.Task;
import com.report.entity.response.TaskPageResponse;
import com.report.entity.response.search.TaskSearchListRequest;
import com.report.service.TaskService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;
import com.report.utils.SendMail;

@Service
@Transactional(rollbackOn = Exception.class)
public class TaskServiceImpl extends AbstractBaseDao implements TaskService {


    @Autowired
    private TaskDao taskDao;
    
    @Autowired
    public SendMail sendMail;
    
    @Autowired
    public UserDao userDao;
    
    private void getTaskEntity(String json, Task entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "team_id")) {
            entity.setTeamId(DataUtil.getJsonInteger(jsonObject, "team_id"));
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
        if (DataUtil.hasMember(jsonObject, "content")) {
            entity.setContent(DataUtil.getJsonString(jsonObject, "content"));
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
        if (DataUtil.hasMember(jsonObject, "unit")) {
            entity.setUnit(DataUtil.getJsonString(jsonObject, "unit"));
        }
        if (DataUtil.hasMember(jsonObject, "status")) {
            entity.setStatus(DataUtil.getJsonString(jsonObject, "status"));
        }
    }

    @Override
    public ResultBean getTaskById(Integer taskId) throws Exception {
        Task report = taskDao.getTaskById(taskId);
        return new ResultBean(report, "200", "OK");
    }

    @Override
    public ResultBean add(HttpServletRequest request, String json) throws Exception {
        Task entity = new Task();
        entity.setCreated(DataUtil.getLocalDateTime());
        entity.setDelFlg(Constants.DEL_FLG_0);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        this.getTaskEntity(json, entity);
        taskDao.add(entity);
        sendMail.sendMailAddTask(userDao.getUser(entity.getUserId()), entity.getName(), entity.getCreatedbyUsername());
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean update(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer taskId = DataUtil.getJsonInteger(jsonObject, "task_id");
        Task entity = taskDao.getTaskById(taskId);
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setLastmodifiedbyUsername(email);
        if(delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
        }else {
            this.getTaskEntity(json, entity);
        }
        taskDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean getTasksByTeam(Integer teamId, String name, String date, Integer page, Integer size) {
        TaskSearchListRequest searchListRequest = new TaskSearchListRequest();
        TaskPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("teamId", teamId, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("taskId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        } 
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (TaskPageResponse) taskDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getTasksByUser(Integer user_id, String name, String date, Integer page, Integer size) {
        TaskSearchListRequest searchListRequest = new TaskSearchListRequest();
        TaskPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("userId", user_id, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("taskId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        } 
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (TaskPageResponse) taskDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

}
