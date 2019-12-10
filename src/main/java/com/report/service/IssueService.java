package com.report.service;


import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface IssueService {
    public ResultBean getIssueById(Integer issueId) throws Exception;
    public ResultBean getIssueDetail(Integer issueId) throws Exception;
    public ResultBean getIssuesByProject(Integer issueId, String name, String date, Integer page, Integer size);
    public ResultBean add(HttpServletRequest request, String json) throws Exception;
    public ResultBean update(HttpServletRequest request, String json) throws Exception;
    public ResultBean getIssuesByUser(Integer user_id, String name, String date, Integer page, Integer size);
    public ResultBean getIssues(HttpServletRequest request, Integer project_id, String name, String date, String status, String work_type, Integer page, Integer size);
}
