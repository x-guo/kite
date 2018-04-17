package com.example.server2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenyu.guo
 * @date 2018/3/3.
 */

@RestController
@RequestMapping(value = "/hello")
@Slf4j
public class HelloWorld {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/world",method = RequestMethod.GET)
    public String helloWorld() {
        ServiceInstance instance = discoveryClient.getInstances("eureka-server").get(0);
        log.info("/hello/world, host:{} , service_id:{}",instance.getHost(), instance.getServiceId());
        return "hello world";
    }

}
