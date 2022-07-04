package com.mingyang.bootlaunch.thread;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 21:30
 * @version: 1.0
 */
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码:=====>>>"+i);
        }
    }

    public static void main(String[] args) {
        TestThread3 thread3 = new TestThread3();
        new Thread(thread3).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程:=====>>>"+i);
        }
    }
}
