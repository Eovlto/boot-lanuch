package com.mingyang.bootlaunch.Juc.lock;

import com.mingyang.bootlaunch.Juc.lock.bo.ShareResource;

public class ThreadDemo03 {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                resource.print5(i);
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                resource.print10(i);
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                resource.print15(i);
            }
        },"CC").start();
    }
}
