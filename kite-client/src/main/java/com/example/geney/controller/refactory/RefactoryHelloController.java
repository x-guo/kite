package com.example.geney.controller.refactory;

import com.example.demo.entity.User;
import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenyu.guo
 * @date 2018/3/30.
 */
@RestController
public class RefactoryHelloController implements HelloService {
    @Override
    public String hello4(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @Override
    public User hello5(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name,age);
    }

    @Override
    public String hello6(@RequestBody User user) {
        return "Hello" + user.getUserName() + ", " + user.getAge();
    }
}
