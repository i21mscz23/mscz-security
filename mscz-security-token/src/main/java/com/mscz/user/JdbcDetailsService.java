package com.mscz.user;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 14:44
 */
@Component
public class JdbcDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new JwtUserDetails("username","$2a$10$n9yKwQ9QXfYJBhXSmSyyvexlf6Iq0peoON15FagiWPFULIbsLFdU6",
                20000L, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

//        return User.withUsername("username")
//                .password("$2a$10$n9yKwQ9QXfYJBhXSmSyyvexlf6Iq0peoON15FagiWPFULIbsLFdU6")
//                .authorities("ROLE_ADMIN")
//                .build();
    }
}
