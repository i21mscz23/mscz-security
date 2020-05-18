package com.mscz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 15:02
 */
@RestController
public class SecurityController {

    @GetMapping("/require")
    public String require(){
        return "require";
    }

    @GetMapping("/resource")
    public String resouce(){
        return "resource";
    }
}
