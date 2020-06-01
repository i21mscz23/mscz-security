package com.mscz.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 15:27
 */
@Component
public class JdbcDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.withUsername("username")
                .password("$2a$10$iYZ8LVJkgroiZRT6FwxPeuXKVk3GVlM8WJGwqhrn3Evd1J6Ye2hji")
                .authorities("ROLE_ADMIN")
                .build();
    }
}
