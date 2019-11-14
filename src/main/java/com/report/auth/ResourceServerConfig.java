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
                .antMatchers("/api/leaders").access("hasRole('ADMIN') or hasRole('LEADER')")
                .antMatchers("/api/teams/user/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/api/team").access("hasRole('ADMIN') or hasRole('LEADER')")
                .antMatchers("/api/teams").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/api/users").access("hasRole('ADMIN')")
                .antMatchers("/api/user/**").access("hasRole('ADMIN')")
                .antMatchers("/api/users/add-team").access("hasRole('ADMIN')or hasRole('LEADER')")
                .antMatchers("/api/bugs/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/api/bug/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/api/task/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/api/tasks/**").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .antMatchers("/logout").access("hasRole('ADMIN') or hasRole('MEMBER') or hasRole('LEADER')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }
}