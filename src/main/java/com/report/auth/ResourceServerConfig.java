package com.report.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
                http
                .anonymous().disable()
                .requestMatchers().antMatchers("/api/**")
                .and().authorizeRequests()
                .antMatchers("/api/managers").access("hasRole('ADMIN')")
                .antMatchers("/api/project").access("hasRole('ADMIN')")
                .antMatchers("/api/project/{project_id}").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/projects").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/projects/add-issue").access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/api/users").access("hasRole('ADMIN')")
                .antMatchers("/api/user/**").access("hasRole('ADMIN')")
                .antMatchers("/api/users/add-project").access("hasRole('ADMIN')")
                .antMatchers("/api/issue/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/issues/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/issues").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/issue-detail/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/logout").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/info-user").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/change-pass").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .antMatchers("/api/managers/project/{project_id}").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('MANAGER')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }
}