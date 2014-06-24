package com.dudo.deadlock;

/**
 * User: dudu
 * Date: 14-3-26
 * Time: 下午4:49
 */
public class Cat extends Thread {
    @Override
    public void run() {



//        System.out.println("Cat 1");
//        synchronized (DeadLock.b) {
//            System.out.println("Cat 2");
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                System.out.println("Cat 3");
//                e.printStackTrace();
//            }
//            synchronized (DeadLock.a) {
//                System.out.println("Cat 4");
//                try {
//                    sleep(3000);
//                } catch (InterruptedException e) {
//                    System.out.println("Cat 5");
//                    e.printStackTrace();
//                }
//                System.out.println("Cat 6");
//            }
//        }
    }
}