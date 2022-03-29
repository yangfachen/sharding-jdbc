package com.yang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2022/1/14 18:22
 * @Created by yangchen
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/send")
    public String send(){

        return "hello word";
    }
}
