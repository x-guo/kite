package com.example.demo.config;

import com.example.demo.entity.User;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * @author zhenyu.guo
 * @date 2018/3/22.
 */
public class UserObservableCommand extends HystrixObservableCommand {


    private RestTemplate restTemplate;
    private Long id;

    protected UserObservableCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable construct() {
        return Observable.create((Observable.OnSubscribe<User>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    User user = restTemplate.getForObject("http://KITE/user/{1}", User.class, id);
                    subscriber.onNext(user);
                    subscriber.unsubscribe();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }

        });
    }
}
