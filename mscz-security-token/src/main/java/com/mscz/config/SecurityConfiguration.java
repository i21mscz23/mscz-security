package com.mscz.config;

import com.mscz.authentication.JwtAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Description 安全适配器
 * @Author lixiao
 * @Date 2020/5/16 14:06
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler userAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/require")
//                .loginProcessingUrl("/jwt/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);

        http
                    .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                    .accessDeniedHandler(userAccessDeniedHandler)
                .and()

                    .authorizeRequests()
                    .antMatchers(
                            "/require",
                            "/jwt/login"
                    )
                    .permitAll()
                    .anyRequest().access("@permissionService.hasPermission(request, authentication)")
                .and()
                    .apply(jwtAuthenticationSecurityConfig)
                .and()
                    //禁用session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
