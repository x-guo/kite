package com.example.demo.configs;

import com.example.demo.entity.User;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhenyu.guo
 * @date 2018/4/18.
 */
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello1(@RequestParam(value = "name") String name) {
        return "error1";
    }

    @Override
    public User hello2(@RequestHeader(value = "name") String name, @RequestHeader("age") Integer age) {
        return new User("hello2", 25);
    }

    @Override
    public String hello3(@RequestBody User user) {
        return "error3";
    }
}
