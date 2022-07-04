package com.mingyang.bootlaunch.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 21:30
 * @version: 1.0
 */
public class TestThread6 implements Callable<Boolean> {

    private String url;
    private String name;

    public TestThread6(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDowenLoader dowenLoader = new WebDowenLoader();
        dowenLoader.download(url, name);
        System.out.println("下载了文件名为:"+name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread6 bilibili = new TestThread6("https://i0.hdslb.com/bfs/archive/956de2627e2cc1a9da53ea1d8762cea33e8ea6e5.png@3840w_360h_1c_90q.webp", "bilibili");
        TestThread6 bilibili1 = new TestThread6("https://i0.hdslb.com/bfs/banner/aa4aa70fa356ce4f096fecbbaae3903155fe75ce.jpg@976w_550h_1c.webp", "bilibili1");
        TestThread6 bilibili2 = new TestThread6("https://i0.hdslb.com/bfs/banner/aa4aa70fa356ce4f096fecbbaae3903155fe75ce.jpg@976w_550h_1c.webp", "bilibili2");

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Boolean> future = service.submit(bilibili);
        Future<Boolean> future1 = service.submit(bilibili1);
        Future<Boolean> future2 = service.submit(bilibili2);

        Boolean aBoolean = future.get();
        Boolean aBoolean1 = future1.get();
        Boolean aBoolean2 = future2.get();

        service.shutdown();
    }

    class WebDowenLoader {
        public void download(String url, String fileName) {
            try {
                FileUtils.copyURLToFile(new URL(url), new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
