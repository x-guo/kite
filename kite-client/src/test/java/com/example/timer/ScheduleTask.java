package com.example.timer;

import java.util.concurrent.TimeUnit;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */
public class ScheduleTask {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int d = 1;
            new Thread(() -> {
                System.out.println(System.currentTimeMillis());
                new Order(d,new ScheduleTime(15, TimeUnit.SECONDS),0);
            }).start();
        }
    }

}
