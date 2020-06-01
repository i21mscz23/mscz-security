package com.mscz.config;

import com.mscz.handler.WebSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @Description 资源服务配置
 * @Author lixiao
 * @Date 2020/5/18 17:19
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceConfiguration extends ResourceServerConfigurerAdapter {



    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private WebSecurityExpressionHandler authorizeExpressionHandler;



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .expressionHandler(authorizeExpressionHandler);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                //认证服务器允许通过
//                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().access("#permissionService.hasPermission(request, authentication)");
    }

}
