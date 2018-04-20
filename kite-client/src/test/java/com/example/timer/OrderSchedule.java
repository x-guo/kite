package com.example.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */
public class OrderSchedule {
    private static ScheduledExecutorService manager;
    private static OrderSchedule orderSchedule;

    static{
        manager = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private OrderSchedule(){
    }

    public synchronized static OrderSchedule getInstance(){
        if(orderSchedule == null){
            orderSchedule = new OrderSchedule();
        }
        return orderSchedule;
    }

    /**
     * 预约订单时调用，一段时间后订单失效
     */
    public void startTask(OrderFailure task,ScheduleTime time){
        manager.schedule(task,time.getDelay(),time.getTime());
    }
}