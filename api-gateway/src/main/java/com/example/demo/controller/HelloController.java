package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */

@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

}
