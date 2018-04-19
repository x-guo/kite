package com.example.demo.configs;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author zhenyu.guo
 * 开启指定方法的所有日志打印
 * @date 2018/4/19.
 */

//@Configuration
public class LoggerFeignConfiguration {

    @Bean
    Logger.Level loggerFeignLevel() {
        return Logger.Level.FULL;
    }

}
