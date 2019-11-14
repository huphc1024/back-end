package com.report.service.impl;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.BugDao;
import com.report.entity.Bug;
import com.report.entity.ResultBean;
import com.report.entity.request.BugPageResponse;
import com.report.entity.response.search.BugSearchListRequest;
import com.report.service.BugService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;

@Service
@Transactional(rollbackOn = Exception.class)
public class BugServiceImpl extends AbstractBaseDao implements BugService {

    private static final Logger log = LoggerFactory.getLogger(BugServiceImpl.class);

    @Autowired
    private BugDao bugDao;
    
    @Override
    public ResultBean getBugById(Integer bugId) throws Exception {
        log.info("### getBugById() START ###");
        Bug report = bugDao.getBugById(bugId);
        if (Objects.isNull(report)) {
            throw new Exception("Không tìm thấy Record");
        }
        log.info("### getBugById() END ###");
        return new ResultBean(report, "200", "OK");
    }

    @Override
    public ResultBean addBug(HttpServletRequest request, String json) throws Exception {
        Bug entity = new Bug();
        entity.setCreated(DataUtil.getLocalDateTime());
        entity.setDelFlg(Constants.DEL_FLG_0);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        this.getBugEntity(json, entity);
        bugDao.add(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean updateBug(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer bugId = DataUtil.getJsonInteger(jsonObject, "bug_id");
        Bug entity = bugDao.getBugById(bugId);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        entity.setLastmodifiedbyUsername(email);
        if(delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
        }else {
            this.getBugEntity(json, entity);
        }
        bugDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    private void getBugEntity(String json, Bug entity) throws Exception {
        log.info("### getBugEntity START ###");
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "user_id")) {
            entity.setUserId(DataUtil.getJsonInteger(jsonObject, "user_id"));
        }
        if (DataUtil.hasMember(jsonObject, "task_id")) {
            entity.setTaskId(DataUtil.getJsonInteger(jsonObject, "task_id"));
        }
        if (DataUtil.hasMember(jsonObject, "name")) {
            entity.setName(DataUtil.getJsonString(jsonObject, "name"));
        }
        if (DataUtil.hasMember(jsonObject, "content")) {
            entity.setContent(DataUtil.getJsonString(jsonObject, "content"));
        }
        if (DataUtil.hasMember(jsonObject, "status")) {
            entity.setStatus(DataUtil.getJsonString(jsonObject, "status"));
        }
        log.info("### getBugEntity() END ###");
    }

    @Override
    public ResultBean getBugsByUser(Integer user_id, String name, String date, Integer page, Integer size) {
        BugSearchListRequest searchListRequest = new BugSearchListRequest();
        BugPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("userId", user_id, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("bugId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        } 
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (BugPageResponse) bugDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean getTaskByUser(Integer task_id, String name, String date, Integer page, Integer size) {
        BugSearchListRequest searchListRequest = new BugSearchListRequest();
        BugPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("taskId", task_id, true);
        searchListRequest.addSearchField("name", name, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("bugId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        } 
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (BugPageResponse) bugDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

}
