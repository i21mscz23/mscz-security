package com.mscz.rbac;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2019/10/18 10:52
 */
@Component
public class PermissionServiceImpl implements PermissionService {

    /**
     * 每次用户请求通过uri判断是否有权限访问
     * @param request
     * @param authentication
     * @return
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        //不允许匿名访问
        if(authentication instanceof AnonymousAuthenticationToken){
            throw new AccessTokenRequiredException(null);
        }
        //TODO 该用户时候有权限访问

//        return RandomUtils.nextInt() % 2 == 0;
        return true;
    }
}
