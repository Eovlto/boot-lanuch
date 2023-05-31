package com.mingyang.bootlaunch.Juc.lock;

import com.mingyang.bootlaunch.Juc.lock.bo.LTicketBO;

public class LSaleTicket {
    public static void main(String[] args) {
        LTicketBO ticketBO = new LTicketBO();

       new Thread(()->{
           for (int i = 0; i < 40; i++) {
               ticketBO.sale();
           }
       },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticketBO.sale();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticketBO.sale();
            }
        },"CC").start();
    }
}
