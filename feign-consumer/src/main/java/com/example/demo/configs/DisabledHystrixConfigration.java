package com.example.demo.configs;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author zhenyu.guo
 * @date 2018/4/18.
 */

//@Configuration
public class DisabledHystrixConfigration {

    @Scope("prototype")
    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

}
