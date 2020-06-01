package com.mscz.config.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 15:30
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancerEnhance;

    @Autowired
    private UserDetailsService jdbcDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtTokenEnhancer;

    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //通过链式加强器增加jwt功能
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        if(jwtTokenEnhancerEnhance != null ){
            enhancers.add(jwtTokenEnhancerEnhance);
        }

        enhancers.add(jwtTokenEnhancer);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints
                .userDetailsService(jdbcDetailsService)
                .tokenStore(tokenStore)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtTokenEnhancer)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*基于jdbc*/
        clients.jdbc(dataSource);
        //基于内存模式
        /*clients.inMemory()
                .withClient("proxy")
                .secret(passwordEncoder.encode("123456"))
                .scopes("read","write")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(7200)
                .authorizedGrantTypes("password","refresh_token");*/
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //获取秘钥需要客户端认证
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");
    }
}
