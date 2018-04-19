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

    /*@Bean
    Logger.Level loggerFeignLevel() {
        //NONE 不记录任何信息 BASIC:记录请求方法URL以及响应状态码和执行时间 HEADERS basic+请求头信息  FULL:记录所有的请求响应明细
        return Logger.Level.FULL;
    }*/

}
