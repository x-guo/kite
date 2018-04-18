package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenyu.guo
 * @date 2018/3/30.
 */

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private HelloService helloService;


    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello1("DIDI")).append("\n");
        sb.append(helloService.hello2("DIDI",25)).append("\n");
        sb.append(helloService.hello3(new User(1L,"DIDI","34523452435",2,Boolean.TRUE,25))).append("\n");
        return sb.toString();
    }

}
