package com.mingyang.bootlaunch.thread;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/28 22:49
 * @version: 1.0
 */
public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface MArray{

    void HappyMarry();
}

// 真实角色
class You implements MArray{
    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了");
    }
}

// 代理角色
class WeddingCompany implements MArray{
    private MArray taraget;

    public WeddingCompany(MArray taraget) {
        this.taraget = taraget;
    }

    @Override
    public void HappyMarry() {
        before();
        this.taraget.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚之后.....");
    }

    private void before() {
        System.out.println("结婚之前布置现场");
    }
}
