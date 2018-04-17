package com.example.demo.controller;

import com.example.demo.config.UserCommand;
import com.example.demo.entity.User;
import com.example.demo.service.interfaces.IHelloService;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhenyu.guo
 * @date 2018/3/3.
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private IHelloService helloService;

    /*private RestTemplate restTemplate;
    private HystrixCommand.Setter setter;*/

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.helloService();
    }

    @RequestMapping(value = "/ribbon-consumer2",method = RequestMethod.GET)
    public String helloConsumer2() {
        return helloService.helloService2();
    }
    /*@RequestMapping(value = "find-user", method = RequestMethod.GET)
    public User findUser() throws ExecutionException, InterruptedException {
        User user = new UserCommand(setter,restTemplate,1L).execute();
        //异步获取数据
        Future<User> future = new UserCommand(setter, restTemplate, 1L).queue();
        User user1 = future.get();

        Observable<User> observe = new UserCommand(setter, restTemplate, 1L).observe();
        observe.retry(5).filter(t -> StringUtils.isEmpty(t.getId())).subscribe(result -> {
                result.getId();
           }
        );

        Observable<User> userObservable = new UserCommand(setter, restTemplate, 1L).toObservable();



        return user;

    }*/

}
