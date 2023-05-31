package com.mingyang.bootlaunch.Juc.sync.bo;

import lombok.Data;

@Data
public class TicketBO {
    private int number;

    public TicketBO() {
        this.number = 30;
    }

    public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，剩余："+number);
        }
    }
}
