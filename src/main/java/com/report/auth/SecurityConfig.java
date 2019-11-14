package com.report.auth;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;

    /**
     * Configure login with user information in database
     * @param AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //    @Override
    //    public void configure(WebSecurity web) throws Exception {
    //        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    //    }

    /**
     * Configure information http security
     * @param HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set session managermanet and disable CSRF.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
        // Set URL permit.
        http.authorizeRequests().antMatchers("/oauth/token").permitAll();
        http.authorizeRequests().antMatchers("/api/get_address_list").permitAll();
        // Any request # URL permit by pass authenticated
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().realmName("CRM_REALM");
    }

    /**
     * Crate AuthenticationManager.
     * @return AuthenticationManager
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Create JdbcTokenStore.
     * @return JdbcTokenStore
     */
    @Bean
    @Primary
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * Create TokenStoreUserApprovalHandler.
     * @param tokenStore
     * @return TokenStoreUserApprovalHandler
     */
    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    /**
     * Create ApprovalStore.
     * @param tokenStore
     * @return ApprovalStore
     */
    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    /**
     * Create PasswordEncoder.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("Accept-Language");
        config.addAllowedHeader("Content-Language");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("responseType");
        config.addAllowedHeader("Access-Control-Allow-Headers");
        config.addAllowedMethod("*");
        config.addExposedHeader(
                "Content-Disposition, Access-Control-Expose-Headers, Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Custom-Filter-Header");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
