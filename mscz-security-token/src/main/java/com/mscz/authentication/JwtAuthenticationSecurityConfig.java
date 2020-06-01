package com.mscz.authentication;

import com.mscz.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 14:37
 */
@Component
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RsaVerifier rsaVerifier;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        jwtAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);

        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
        jwtAuthenticationProvider.setUserDetailsService(userDetailsService);
        jwtAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(rsaVerifier);

        builder.authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //请求结构拦截
                .addFilterAfter(authenticationFilter,JwtAuthenticationFilter.class);
    }
}
