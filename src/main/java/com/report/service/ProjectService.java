package com.report.service;

import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;

public interface ProjectService {

    public ResultBean getProjectById(Integer ProjectId);

    public ResultBean addProject(HttpServletRequest request, String json) throws Exception;

    public ResultBean updateProject(HttpServletRequest request, String json) throws Exception;

    public ResultBean getProjects(HttpServletRequest request, String name, String date, Integer page, Integer size);

    public ResultBean getProjects(HttpServletRequest request);

}
