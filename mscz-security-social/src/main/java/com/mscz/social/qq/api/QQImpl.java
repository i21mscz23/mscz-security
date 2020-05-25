package com.mscz.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/23 14:58
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 通过access_token 获取 openId 的地址（openId对应腾讯qq账号的标识）
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息地址
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String appId;

    private String openId;


    public QQImpl(String accessToken, String appId) {
        //以参数形式请求
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //请求获取openId
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        result = StringUtils.substringBetween(result, "(", ")");
        this.openId = JsonPath.read(result, "$.openid");
    }
    
    /**
     * 通过请求从腾讯服务中获取qq用户信息
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo userInfo = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
