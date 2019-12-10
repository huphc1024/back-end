package com.report.dao;

import java.util.List;

import com.report.entity.Report;
import com.report.entity.request.ReportSearchListRequest;
import com.report.entity.response.search.ReportPageResponse;

public interface ReportDao {

    public Report getReportById(Integer reportId);

    public List<Report> getReportByIssue(Integer issueId);

    public Report add(Report entity);

    public Report update(Report entity);

    public ReportPageResponse findAll(ReportSearchListRequest searchListRequest);
}
