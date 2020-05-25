package com.mscz.social.wechat.api;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 14:20
 */
public interface WeChat {

    WeChatUserInfo getUserInfo(String openId);
}
