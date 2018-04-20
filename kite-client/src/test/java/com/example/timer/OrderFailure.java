package com.example.timer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhenyu.guo
 * @date 2018/4/20.
 */
public class OrderFailure implements Runnable {
    private Order order;
    private static Lock lock = new ReentrantLock();

    public OrderFailure(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        if (isPay()) System.out.println("订单已付款，不执行失效任务");
        else {
            lock.lock();
            try {
                order.setStatus(-1);
                System.out.println("id为" + order.getId() + "的订单,失效任务执行");
                System.out.println(System.currentTimeMillis());
            } finally {
                lock.unlock();
            }


        }
    }

    /**
     * 返回订单是否付款
     *
     * @return
     */
    public boolean isPay() {
        if (order.getStatus() == 1) return true;
        else return false;
    }

}