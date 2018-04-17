package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenyu.guo
 * @date 2018/3/30.
 */

@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping(value = "/hello4", method = RequestMethod.GET)
    String hello4(@RequestParam("name") String name);

    @RequestMapping(value = "/hello5", method = RequestMethod.GET)
    User hello5(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello6", method = RequestMethod.GET)
    String hello6(@RequestBody User user);

}
