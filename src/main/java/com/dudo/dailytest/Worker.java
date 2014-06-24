package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 下午2:25
 */

import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {
    private static final long STARTTIME = System.currentTimeMillis();
    private final Object lock = new Object();
    private static long logCount = 1;
    private volatile boolean quittingTime = false;

    public static void main(String[] args) throws InterruptedException {
        println("begin");
        final Worker worker = new Worker();
        worker.start();
        Timer t = new Timer(true); // Daemon thread
        t.schedule(new TimerTask() {
            public void run() {
                worker.keepWorking();
            }
        }, 500);
        Thread.sleep(400);
        worker.quit();
//        Thread.sleep(1000);
    }

    private static void println(String... strs) {
        if (strs == null) return;
        StringBuffer stringBuffer = new StringBuffer();

        for (String s : strs) {
            stringBuffer.append(s).append(" ");
        }
        System.out.format("%d[%ss]\t(thread:%s)\t-- %s",
                logCount++,System.currentTimeMillis() - STARTTIME,
                Thread.currentThread().getId(),
                stringBuffer.toString());
        System.out.println();
    }

    public void run() {
        while (!quittingTime)
            pretendToWork();
        println("Beer is good");
        try {
            sleep(2000);
            println("over");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void pretendToWork() {
        try {
            println("pretendToWork!");
            Thread.sleep(300); // Sleeping on the job?
        } catch (InterruptedException ex) {
        }
    }

    // It's quitting time, wait for worker - Called by good boss
    void quit() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            println("quit!");
            join();
//            join(2);
            println("The goodness boss said:join!");
        }
    }

    // Rescind quitting time - Called by evil boss
    void keepWorking() {
        synchronized (lock) {
            quittingTime = false;
            println("The evil boss said:working!");
            println("The evil boss said:working!");
            println("The evil boss said:working!");
            println("The evil boss said:working!");
            println("The evil boss said:working!");
        }
    }
}