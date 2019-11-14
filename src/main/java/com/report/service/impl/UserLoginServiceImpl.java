package com.report.service.impl;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import com.report.dao.UserDao;
import com.report.entity.User;
import com.report.entity.response.UserLogin;
import com.report.entity.response.UserRoleResponse;
import com.report.service.UserLoginService;
import com.report.utils.DataUtil;

@Service
@Transactional(rollbackOn = Exception.class)
@Order(Ordered.LOWEST_PRECEDENCE)
public class UserLoginServiceImpl implements UserLoginService{
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ConsumerTokenServices tokenServices;

    @Override
    public UserLogin login(String userName) {
        UserLogin userLoginResponse = new UserLogin();
        User userLogin = userDao.findOne(userName);
        if (Objects.nonNull(userLogin)) {
            BeanUtils.copyProperties(userLogin, userLoginResponse);
            List<UserRoleResponse> roles = userDao.getUserRoleByUserId(userLogin.getUserId());
            userLoginResponse.setRoles(roles);
        }
        return userLoginResponse;
    }

    @Override
    public void logout(HttpServletRequest request) {
        tokenServices.revokeToken(DataUtil.getAccessToken(request));
    }

}
