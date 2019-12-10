package com.report.dao;

import java.util.List;

import com.report.entity.Issue;
import com.report.entity.request.IssueSearchListRequest;
import com.report.entity.response.IssueResponse;
import com.report.entity.response.search.IssuePageResponse;

public interface IssueDao {
    public List<Issue> getIssueByProject(Integer projectId);
    public List<Issue> getIssueByUser(Integer userId);
    public Issue getIssueById(Integer issueId);
    public IssueResponse getDetailIssue(Integer issueId);
    public void add(Issue issue);
    public void update(Issue issue);
    public IssuePageResponse findAll(IssueSearchListRequest searchListRequest);
}
