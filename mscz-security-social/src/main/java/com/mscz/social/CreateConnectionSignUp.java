package com.mscz.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Description 社交登陆注册主力逻辑
 * @Author lixiao
 * @Date 2020/5/24 00:11
 */
@Component
public class CreateConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //通过第三方返回的信息作存储为用户信息（userconnection中的userId）
        return connection.getDisplayName();
    }
}
