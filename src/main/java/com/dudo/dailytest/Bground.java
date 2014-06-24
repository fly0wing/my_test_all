package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-6
 * Time: 下午5:59
 */
public class Bground extends Thread {
    public static void main(String[] args) {
        Bground a = new Bground();
        a.run();
    }

    public void start() {
        for(int i=0; i<10; i++) {
            System.out.println("Value of i=" + i);
        }
    }
}
