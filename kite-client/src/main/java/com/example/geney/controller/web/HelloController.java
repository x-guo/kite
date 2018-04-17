package com.example.geney.controller.web;

import com.example.geney.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author zhenyu.guo
 * @date 2018/2/27.
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/world",method = RequestMethod.GET)
    public String helloWorld() throws InterruptedException {
        ServiceInstance instance = discoveryClient.getInstances("kite").get(0);

        /*int sleep = new Random().nextInt(2000);
        log.info("sleepTime:" + sleep);
        Thread.sleep(sleep);*/

        log.info("/hello/world, host:{} , service_id:{}",instance.getHost(), instance.getServiceId());
        return "hello world";
    }

    @RequestMapping(value = "/hello1")
    public String hello1(@RequestParam String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello2")
    public User hello2(@RequestHeader String name,@RequestHeader Integer age) {
        return new User(1L,name,"3425234523452345",2,Boolean.TRUE,age);
    }

    @RequestMapping(value = "/hello3")
    public String hello3(@RequestBody User user) {
        return "hello" + user.getUserName() + ", " + user.getAge();
    }

}
