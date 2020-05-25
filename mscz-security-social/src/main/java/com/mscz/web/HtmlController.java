package com.mscz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/23 22:48
 */
@Controller
public class HtmlController {

    @GetMapping("/index")
    public String index(){
        return "login";
    }
}
