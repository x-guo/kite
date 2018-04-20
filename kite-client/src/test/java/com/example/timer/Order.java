package com.example.timer;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */
public class Order {
    private Integer id; //订单id
    private Integer status; //订单状态，0为未付款，1为已付款，-1为已失效
    private ScheduleTime time;
    public Order(Integer id,ScheduleTime time,Integer status){
        this.id = id;
        this.time = time;
        this.status = status;
        OrderSchedule.getInstance().startTask(new OrderFailure(this), time);
    }

    public boolean isAlive(){
        return status != -1;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}