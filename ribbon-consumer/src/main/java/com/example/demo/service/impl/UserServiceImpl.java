package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.interfaces.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhenyu.guo
 * @date 2018/3/27.
 */
@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "100")})
    @Override
    public User find(Long id) {
        User user = restTemplate.getForObject("http://KITE/user/{1}", User.class, id);
        return user;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @Override
    public List<User> findAll(List<Long> ids) {
        List<User> list = restTemplate.getForObject("http://KITE/user?ids={1}", List.class, StringUtils.join(ids, ","));
        return list;
    }

    private User fallback() {
        return new User();
    }
}
