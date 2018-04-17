package com.example.demo.config.collapser;

import com.example.demo.entity.User;
import com.example.demo.service.interfaces.IUserService;
import com.netflix.hystrix.HystrixCommand;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * @author zhenyu.guo
 * @date 2018/3/27.
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    IUserService userService;
    List<Long> userIds;

    protected UserBatchCommand(IUserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.findAll(userIds);
    }
}
