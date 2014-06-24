package com.dudo.deadlock;

/**
 * User: dudu
 * Date: 14-3-26
 * Time: 下午4:49
 */
public class Tom extends Thread {
    @Override
    public void run() {
        System.out.println("Tom 1");
        synchronized (DeadLock.a) {
            System.out.println("Tom 2");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Tom 3");
                e.printStackTrace();
            }
            synchronized (DeadLock.b) {
                System.out.println("Tom 4");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Tom 5");
                    e.printStackTrace();
                }
                System.out.println("Tom 6");
            }
        }
    }
}