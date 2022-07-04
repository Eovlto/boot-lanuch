package com.mingyang.bootlaunch.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 21:39
 * @version: 1.0
 */
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDowenLoader dowenLoader = new WebDowenLoader();
        dowenLoader.download(url, name);
        System.out.println("下载了文件名为:"+name);
    }

    public static void main(String[] args) {
        TestThread2 bilibili = new TestThread2("https://i0.hdslb.com/bfs/archive/956de2627e2cc1a9da53ea1d8762cea33e8ea6e5.png@3840w_360h_1c_90q.webp", "bilibili");
        TestThread2 bilibili1 = new TestThread2("https://i0.hdslb.com/bfs/banner/aa4aa70fa356ce4f096fecbbaae3903155fe75ce.jpg@976w_550h_1c.webp", "bilibili1");
        TestThread2 bilibili2 = new TestThread2("https://i0.hdslb.com/bfs/banner/aa4aa70fa356ce4f096fecbbaae3903155fe75ce.jpg@976w_550h_1c.webp", "bilibili2");

        bilibili.start();
        bilibili1.start();
        bilibili2.start();
    }

    class WebDowenLoader {
        public void download(String url,String fileName){
            try {
                FileUtils.copyURLToFile(new URL(url),new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
