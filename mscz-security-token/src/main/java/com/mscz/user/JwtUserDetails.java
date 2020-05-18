package com.mscz.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 16:37
 */
public class JwtUserDetails implements UserDetails {

    private String username;

    private String password;

    private Long expirationTime;

    private Long loginTime;

    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public JwtUserDetails(String username, String password, Long expirationTime, List<GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.expirationTime = expirationTime;
        this.authorities = authorities;
        this.loginTime = System.currentTimeMillis();

    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    @Override
    public boolean isAccountNonExpired() {
        long now = System.currentTimeMillis();
        if((now - this.loginTime) > this.expirationTime){
            return false;
        }
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }




}
