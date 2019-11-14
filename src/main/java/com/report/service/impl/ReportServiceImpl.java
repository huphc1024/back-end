package com.report.service.impl;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.ReportDao;
import com.report.entity.Report;
import com.report.entity.ResultBean;
import com.report.service.ReportService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;

@Service
@Transactional(rollbackOn = Exception.class)
public class ReportServiceImpl extends AbstractBaseDao implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private ReportDao reportDao;

    @Override
    public ResultBean getReportById(Integer reportId){
        log.info("### getReportById() START ###");
        Report task = reportDao.getReportById(reportId);
        if (Objects.isNull(task)) {
            log.info("Không tồn tại report");
        }
        log.info("### getReportById() END ###");
        return new ResultBean(task, "200", "OK");
    }

    @Override
    public ResultBean addReport(HttpServletRequest request, String json) throws Exception {
        Report entity = new Report();
        entity.setCreated(DataUtil.getLocalDateTime());
        entity.setDelFlg(Constants.DEL_FLG_0);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedby(email);
        this.getReportEntity(json, entity);
        reportDao.add(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean updateReport(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        Integer reportId = DataUtil.getJsonInteger(jsonObject, "report_id");
        Report entity = reportDao.getReportById(reportId);
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setLastmodifiedby(email);;
        if(delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
        }else {
            this.getReportEntity(json, entity);
        }
        reportDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    private void getReportEntity(String json, Report entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "task_id")) {
            entity.setTaskId(DataUtil.getJsonInteger(jsonObject, "task_id"));
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
    }

    @Override
    public ResultBean getReportsByTask(Integer taskId) {
        List<Report> reports = reportDao.getReportByTask(taskId);
        if (Objects.isNull(reports)) {
            log.info("Không tồn tại Report nào trong task");
        }
        return new ResultBean(reports, "200", "OK");
    }

}
