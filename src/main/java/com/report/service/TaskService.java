package com.report.service;


import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface TaskService {
    public ResultBean getTaskById(Integer taskId) throws Exception;
    public ResultBean getTasksByTeam(Integer teamId, String name, String date, Integer page, Integer size);
    public ResultBean add(HttpServletRequest request, String json) throws Exception;
    public ResultBean update(HttpServletRequest request, String json) throws Exception;
    public ResultBean getTasksByUser(Integer user_id, String name, String date, Integer page, Integer size);
}
