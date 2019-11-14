package com.report.auth;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.report.entity.response.UserLogin;
import com.report.service.UserLoginService;

@Service
public class OAuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserLoginService userLoginService;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserLogin user = userLoginService.login(userName);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Email: " + userName + " not found.");
        }
        return new OauthUserDetails(user);
    }

}
