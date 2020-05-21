package com.mscz.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description 通过该滤器判断限制 一个用户只有一个token(顶)
 * @Author lixiao
 * @Date 2020/5/21 15:57
 */
public class SingleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
