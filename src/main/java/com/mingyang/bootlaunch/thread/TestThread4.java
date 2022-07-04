package com.mingyang.bootlaunch.thread;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 22:14
 * @version: 1.0
 */
public class TestThread4 implements Runnable {

    private int ticketNums= 10;

    @Override
    public void run() {
        while (true) {

            if(ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread4 thread4 = new TestThread4();
        new Thread(thread4,"小明").start();
        new Thread(thread4,"小花").start();
        new Thread(thread4,"黄牛").start();
    }
}
