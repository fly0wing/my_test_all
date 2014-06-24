package com.dudo.deadlock;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 * User: dudu
 * Date: 14-3-26
 * Time: 下午4:51
 */
public class DeadLock {
    public ThreadLocal<HashMap> map;
    public static byte[] a = new byte[0];
    public static byte[] b = new byte[0];

    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        Tom tom = new Tom();
        cat.start();
        Thread.sleep(1000);
        tom.start();
    }
}
