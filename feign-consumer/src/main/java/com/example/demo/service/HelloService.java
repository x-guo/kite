package com.example.demo.service;

import com.example.demo.configs.HelloServiceFallback;
import com.example.demo.configs.LoggerFeignConfiguration;
import com.example.demo.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhenyu.guo
 * configuration = LoggerFeignConfiguration.class 开启日志
 * @date 2018/3/30.
 */

@FeignClient(name = "KITE", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping("/hello/world")
    String hello();

    @RequestMapping("/hello/hello1")
    String hello1(@RequestParam(value = "name") String name);

    @RequestMapping("/hello/hello2")
    User hello2(@RequestHeader(value = "name") String name, @RequestHeader("age") Integer age);

    @RequestMapping("/hello/hello3")
    String hello3(@RequestBody User user);
}
