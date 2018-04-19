package com.example.demo.configs;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhenyu.guo
 * @date 2018/4/19.
 */

@Configuration
public class LoggerFeignConfiguration {

    @Bean
    Logger.Level loggerFeignLevel() {
        return Logger.Level.FULL;
    }

}
