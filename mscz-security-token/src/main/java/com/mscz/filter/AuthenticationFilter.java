package com.mscz.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscz.authentication.JwtAuthenticationToken;
import com.mscz.user.JwtUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 16:50
 */
public class AuthenticationFilter extends OncePerRequestFilter {


    private RsaVerifier rsaVerifier;

    public AuthenticationFilter(RsaVerifier rsaVerifier) {
        this.rsaVerifier = rsaVerifier;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || StringUtils.isBlank(token)){
            //其他过滤器判断请求是否合法
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析jwt,通过秘钥判断是否被修改过
        Jwt jwt = JwtHelper.decodeAndVerify(token, rsaVerifier);
        String claims = jwt.getClaims();
        //TODO 判断token是否有效
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JwtUserDetails jwtUserDetails = objectMapper.readValue(claims, JwtUserDetails.class);
        boolean accountNonExpired = jwtUserDetails.isAccountNonExpired();
        System.out.println(accountNonExpired);

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken("username","", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        jwtAuthenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
