package com.mscz.config.resource;

import com.mscz.handler.GatewayAccessDeniedHandler;
import com.mscz.handler.GatewayAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Description 资源服务配置
 * @Author lixiao
 * @Date 2020/5/18 17:19
 */
//@Configuration
//@EnableResourceServer
public class OAuth2ResourceConfiguration extends ResourceServerConfigurerAdapter {



//    @Autowired
    private GatewayAccessDeniedHandler accessDeniedHandler;

//    @Autowired
    private GatewayAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
