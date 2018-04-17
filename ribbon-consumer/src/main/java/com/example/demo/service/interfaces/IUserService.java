package com.example.demo.service.interfaces;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author zhenyu.guo
 * @date 2018/3/27.
 */
public interface IUserService {

    User find(Long id);

    List<User> findAll(List<Long> id);

}
