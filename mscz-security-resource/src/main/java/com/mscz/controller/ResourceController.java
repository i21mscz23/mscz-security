package com.mscz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/21 14:44
 */
@RestController
public class ResourceController {


    @GetMapping("/resource")
    public Object resource(Authentication authentication){
        return authentication;
    }
}
