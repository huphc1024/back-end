package com.report.service;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.report.entity.ResultBean;
import com.report.entity.User;

public interface UserService {
    public ResultBean getLeaders() throws Exception;
    public ResultBean getUsersAddTeam() throws Exception;
    public User findOne(String email);
    public ResultBean add(HttpServletRequest request, String json) throws Exception;
    public ResultBean update(HttpServletRequest request, String json) throws Exception;
    public ResultBean reset(String json) throws MessagingException, Exception;
    public ResultBean getUsers(String fullname, String date, Integer page, Integer size);
    public ResultBean changePass(HttpServletRequest request, String json) throws Exception;
    public ResultBean getUsersByTeam(Integer team_id);
}
