package com.mingyang.bootlaunch.Juc.sync;

import com.mingyang.bootlaunch.Juc.sync.bo.TicketBO;

public class SaleTicket {

    public static void main(String[] args) {
        TicketBO ticket = new TicketBO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
