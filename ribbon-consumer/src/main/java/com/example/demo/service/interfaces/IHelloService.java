package com.example.demo.service.interfaces;

import com.example.demo.entity.User;

import java.util.concurrent.Future;

/**
 * @author zhenyu.guo
 * @date 2018/3/16.
 */
public interface IHelloService {

    String helloService();

    Future<User> getUserById(Long id);

    String helloService2();

}
