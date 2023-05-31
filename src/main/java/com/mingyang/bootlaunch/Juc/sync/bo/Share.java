package com.mingyang.bootlaunch.Juc.sync.bo;

public class Share {
    private int number;

    public Share() {
        this.number = 0;
    }

    public synchronized  void incr() {
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " : " + number);

        this.notifyAll();
    }

    public synchronized  void decr() {
        while (number != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " : " + number);

        this.notifyAll();
    }
}
