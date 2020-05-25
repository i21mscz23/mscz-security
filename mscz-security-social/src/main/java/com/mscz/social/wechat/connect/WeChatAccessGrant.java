package com.mscz.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 14:45
 */
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    public WeChatAccessGrant() {
        super("");
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
