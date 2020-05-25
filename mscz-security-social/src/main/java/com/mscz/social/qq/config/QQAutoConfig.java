package com.mscz.social.qq.config;

import com.mscz.social.CurrentUserHolder;
import com.mscz.social.qq.connect.QQConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/23 16:25
 */
@Configuration
public class QQAutoConfig extends SocialConfigurerAdapter {


    @Override
    public UserIdSource getUserIdSource() {
        return new CurrentUserHolder();
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        /**
         * providerId 为过滤地址的后面部分（/qqLogin/callback.do）
         */
        return new QQConnectionFactory("callback.do", "100550231", "69b6ab57b22f3c2fe6a6149274e3295e");
    }
}
