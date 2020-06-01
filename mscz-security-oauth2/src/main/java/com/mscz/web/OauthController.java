package com.mscz.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 17:09
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @GetMapping("/token")
    public String getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException, JsonProcessingException {
        return new ObjectMapper().writeValueAsString(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public Object postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException, JsonProcessingException {
        DefaultOAuth2AccessToken body = (DefaultOAuth2AccessToken) tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return body;
    }


}
