package com.report.service;

import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface BugService {

    public ResultBean getBugById(Integer teamId) throws Exception;

    public ResultBean addBug(HttpServletRequest request, String json) throws Exception;

    public ResultBean updateBug(HttpServletRequest request, String json) throws Exception;

    public ResultBean getBugsByUser(Integer user_id, String name, String date, Integer page, Integer size);

    public ResultBean getTaskByUser(Integer task_id, String name, String date, Integer page, Integer size);
}
