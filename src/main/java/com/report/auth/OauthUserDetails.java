package com.report.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.report.entity.response.UserLogin;
import com.report.entity.response.UserRoleResponse;

public class OauthUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Collection<GrantedAuthority> authorities;
    private String password;
    private String email;
    private UserLogin user;

    public OauthUserDetails(UserLogin user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
        this.user = user;
    }

    private Collection<GrantedAuthority> translate(List<UserRoleResponse> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserRoleResponse role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return authorities;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

}
