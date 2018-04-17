package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.interfaces.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author zhenyu.guo
 * @date 2018/3/16.
 */
@Service
@Slf4j
public class HelloService implements IHelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey",threadPoolKey = "helloService")
    @Override
    public String helloService() {
        long start = System.currentTimeMillis();

        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
        String[] strings = StringUtils.toStringArray(list);
        log.info("数组数据为:" + Arrays.toString(strings));

        long end = System.currentTimeMillis();
        log.warn("执行时间为:" + (end - start));
        //此方法为默认同步方法
        String body = restTemplate.getForEntity("http://KITE/hello/world", String.class).getBody();
        return body;

    }

    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey",threadPoolKey = "helloService")
    @Override
    public String helloService2() {

        String body = restTemplate.getForEntity("http://KITE/hello/world", String.class).getBody();
        return body;

    }


    public Long getUserByIdCacheKey(Long id) {
        return id;
    }

//    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
//    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey", threadPoolKey = "helloService")
    @Override
    public Future<User> getUserById(final Long id) {
        //异步方法请求数据
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://KITE/user/{1}", User.class, id);
            }
        };


    }


    private String helloFallback() {
        return "error";
    }
}
