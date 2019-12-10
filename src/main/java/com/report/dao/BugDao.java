package com.report.dao;

import com.report.entity.Bug;
import com.report.entity.request.BugPageResponse;
import com.report.entity.request.BugSearchListRequest;

public interface BugDao {

    public Bug getBugById(Integer bugId);

    public Bug add(Bug entity);

    public Bug update(Bug entity);

    public BugPageResponse findAll(BugSearchListRequest searchListRequest);
}
