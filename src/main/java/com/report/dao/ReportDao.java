package com.report.dao;

import java.util.List;

import com.report.entity.Report;

public interface ReportDao {

    public Report getReportById(Integer reportId);

    public List<Report> getReportByTask(Integer taskId);

    public Report add(Report entity);

    public Report update(Report entity);
}
