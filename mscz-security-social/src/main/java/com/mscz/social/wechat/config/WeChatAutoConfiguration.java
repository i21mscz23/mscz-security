package com.mscz.social.wechat.config;

import com.mscz.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 14:52
 */
@Configuration
public class WeChatAutoConfiguration extends SocialConfigurerAdapter {

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    protected ConnectionFactory<?> createConnectionFactory() {

        return new WeChatConnectionFactory("wechat", "wxd99431bbff8305a0",
                "60f78681d063590a469f1b297feff3c4");
    }
}
