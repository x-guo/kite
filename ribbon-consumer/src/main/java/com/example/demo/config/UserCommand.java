package com.example.demo.config;

import com.example.demo.entity.User;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhenyu.guo
 * @date 2018/3/22.
 */
public class UserCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandGroupKey")).andCommandKey(GETTER_KEY).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
        this.restTemplate = restTemplate;
        this.id = id;
    }


    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://KITE/user/{1}", User.class, id);
    }

    @Override
    protected User getFallback() {
        return new User();
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void flushCache(Long id) {
        //刷新缓存,根据ID进行清理
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }

}

class UserPostCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private User user;

    protected UserPostCommand(Setter setter, RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    @Override
    protected User run() throws Exception {
        User user = restTemplate.postForObject("http://KITE/user/{1}", this.user, User.class);
        UserCommand.flushCache(user.getId());
        return user;
    }
}
