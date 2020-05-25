package com.mscz.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/24 11:57
 */
@RestController
public class MeController {


    @GetMapping("/me")
    public Object me(Authentication authentication){
        return authentication;
    }

}
