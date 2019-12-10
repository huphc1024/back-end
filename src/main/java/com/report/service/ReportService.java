package com.report.service;

import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface ReportService {

    public ResultBean getReportById(Integer reportId);

    public ResultBean addReport(HttpServletRequest request, String json) throws Exception;

    public ResultBean updateReport(HttpServletRequest request, String json) throws Exception;

    public ResultBean getReportsByIssue(Integer issueId, Integer page, Integer size);
}
