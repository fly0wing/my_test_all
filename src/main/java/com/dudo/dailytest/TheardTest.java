package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-25
 * Time: 下午10:52
 */
public class TheardTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyRunObject();
        Thread t2 = new MyRunObject();
//        t2.setPriority(4);
        t.start();
        t2.start();
//        synchronized(t){
//            t.wait();
//        }
        t.join();
        t2.join();
        System.out.println("3." + System.currentTimeMillis());
    }

    private static class MyRunObject extends Thread {
        @Override
        public void run() {
            System.out.println("1." + this.getName() + "\t" + System.currentTimeMillis());
            Thread.yield();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
            System.out.println("2." + this.getName() + "\t" + System.currentTimeMillis());
        }
    }
}
