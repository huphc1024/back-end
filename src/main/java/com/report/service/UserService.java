package com.report.service;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;
import com.report.entity.User;

public interface UserService {
    public ResultBean getManagers() throws Exception;
    public User findOne(String email);
    public ResultBean add(HttpServletRequest request, String json) throws Exception;
    public ResultBean update(HttpServletRequest request, String json) throws Exception;
    public ResultBean reset(String json) throws MessagingException, Exception;
    public ResultBean getUsers(String fullname, String date, Integer page, Integer size);
    public ResultBean changePass(HttpServletRequest request, String json) throws Exception;
    public ResultBean getUsersByProject(Integer team_id);
    public ResultBean getManByProject(Integer team_id);
    public ResultBean getUsersAddProject();
    public ResultBean getUserInfo(HttpServletRequest request);
    public ResultBean getUserById(Integer user_id);
}
