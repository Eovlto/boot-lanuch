package com.mingyang.bootlaunch.Juc.callable.thread;

import java.util.concurrent.Callable;

public class MyThreadCallable implements Callable {

    @Override
    public Object call() throws Exception {
        return 200;
    }
}
