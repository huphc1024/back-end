package com.report.service;

import javax.servlet.http.HttpServletRequest;

import com.report.entity.response.UserLogin;

public interface UserLoginService {
    
    public UserLogin login(String userName);

    public void logout(HttpServletRequest request);
}
