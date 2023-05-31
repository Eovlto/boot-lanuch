package com.mingyang.bootlaunch.Juc.sync;

import com.mingyang.bootlaunch.Juc.sync.bo.Share;

public class ThreadDemo01 {

    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.incr();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.decr();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.incr();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.decr();
            }
        }, "D").start();
    }
}
