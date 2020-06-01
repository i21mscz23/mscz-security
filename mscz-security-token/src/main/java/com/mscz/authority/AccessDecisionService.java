package com.mscz.authority;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 13:14
 */
@Component("permissionService")
public class AccessDecisionService implements PermissionService{

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        return true;
    }

}
