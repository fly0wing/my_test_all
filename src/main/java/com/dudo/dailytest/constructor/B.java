package com.dudo.dailytest.constructor;

/**
 * 构造方法的执行顺序。。。。
 * Created by zkai on 2015/2/4.
 */
public class B extends A {
    public B() {
        System.out.println("B none");
    }

    public B(String msg) {
        this();
        System.out.println("B msg");
    }

    public static void main(String[] args) {
        new B("");

    }
}
