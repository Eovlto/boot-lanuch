package com.mingyang.bootlaunch.Juc.lock.bo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResource {

    private int flag = 1; // 1:A 2:B 3:C

    private final Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loop) {
        lock.lock();
        try {
            // 1. 判断
            while (flag != 1) {
                c1.await();
            }
            // 2. 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " 轮数 : " + loop);
            }
            // 3. 通知
            flag = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) {
        lock.lock();
        try {
            // 1. 判断
            while (flag != 2) {
                c2.await();
            }
            // 2. 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " : " + loop);
            }
            // 3. 通知
            flag = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) {
        lock.lock();
        try {
            // 1. 判断
            while (flag != 3) {
                c3.await();
            }
            // 2. 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " : " + loop);
            }
            // 3. 通知
            flag = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
