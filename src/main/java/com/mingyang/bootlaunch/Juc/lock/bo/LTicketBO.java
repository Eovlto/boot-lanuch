package com.mingyang.bootlaunch.Juc.lock.bo;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

@Data
public class LTicketBO {
    private int number;

    public LTicketBO() {
        this.number = 30;
    }

    private final ReentrantLock lock = new ReentrantLock();

    public void sale(){
        try {
            lock.lock();
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，剩余："+number);
            }
        } finally {
            lock.unlock();
        }
    }
}
