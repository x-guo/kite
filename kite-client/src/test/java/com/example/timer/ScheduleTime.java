package com.example.timer;

import java.util.concurrent.TimeUnit;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */
public class ScheduleTime {
    private long delay;
    private TimeUnit time;
    public ScheduleTime(long delay,TimeUnit time){
        this.delay = delay;
        this.time = time;
    }
    public long getDelay() {
        return delay;
    }
    public void setDelay(long delay) {
        this.delay = delay;
    }
    public TimeUnit getTime() {
        return time;
    }
    public void setTime(TimeUnit time) {
        this.time = time;
    }

}
