package com.mingyang.bootlaunch.thread;



/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 22:23
 * @version: 1.0
 */
public class TestThread5 implements Runnable {

    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            if("兔子".equals(Thread.currentThread().getName()) && i%10 == 0) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = gameOver(i);
            if(flag) {
                break;
            }

            System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + "===>>>跑了" + i + "步");
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        }

        if (steps >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is :"+ winner);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TestThread5 thread5 = new TestThread5();
        new Thread(thread5,"乌龟").start();
        new Thread(thread5,"兔子").start();
    }
}
