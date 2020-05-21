package com.mscz.config;

import com.mscz.filter.SingleFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/21 15:55
 */
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new SingleFilter(), ExceptionTranslationFilter.class).authorizeRequests().anyRequest().authenticated();

    }
}
