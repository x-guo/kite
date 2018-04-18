package com.example.demo.service;


import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author zhenyu.guo
 * @date 2018/3/30.
 */

@FeignClient(value = "KITE")
public interface RefactoryHelloService extends com.example.demo.service.HelloService {

}
