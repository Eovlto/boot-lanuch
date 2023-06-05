package com.mingyang.bootlaunch.Juc.callable;

import com.mingyang.bootlaunch.Juc.callable.thread.MyThreadCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new MyThreadCallable());

        FutureTask<Integer> task2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" MyThread.run()");
            return 1024;
        });

        new Thread(task2,"B").start();
        new Thread(task,"A").start();

//        while (!task2.isDone()){
//            System.out.println("wait...");
//        }
        System.out.println(task2.get());
        System.out.println(task.get());
        System.out.println(Thread.currentThread().getName()+" :: orver");
    }
}
