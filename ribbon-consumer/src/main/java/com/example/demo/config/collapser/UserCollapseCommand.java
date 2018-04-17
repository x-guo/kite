package com.example.demo.config.collapser;


import com.example.demo.entity.User;
import com.example.demo.service.interfaces.IUserService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.netflix.hystrix.HystrixCollapserKey.Factory.asKey;

/**
 * @author zhenyu.guo
 * @date 2018/3/27.
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Long> {


    private IUserService userService;
    private Long userId;

    public UserCollapseCommand(IUserService userService, Long userId) {
        super(Setter.withCollapserKey(asKey("UserCollapseCommand")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.userService = userService;
        this.userId = userId;
    }

    @Override
    public Long getRequestArgument() {
        return userId;
    }

    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collection) {
        List<Long> userIds = new ArrayList<>(collection.size());
        userIds.addAll(collection.stream().map(CollapsedRequest :: getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService, userIds);
    }

    @Override
    protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, Long>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Long> userLongCollapsedRequest : collection) {
            User user = batchResponse.get(count++);
            userLongCollapsedRequest.setResponse(user);
        }

    }
}
