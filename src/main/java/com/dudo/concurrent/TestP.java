package com.dudo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zkai on 2015/1/29.
 */
public class TestP {
    public static void main(String args[]) throws InterruptedException {
        // 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
        int concurrent = 10;//并发数
        int delay = 20; // 间隔
        int count = 1000;//预计执行多少次

        CountDownLatch countDownLatch = new CountDownLatch(count);
        AtomicInteger counter = new AtomicInteger(0);//计数器，实际执行

        long start = System.currentTimeMillis();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(concurrent);
        // 创建实现了runnable接口的对象
        long end = 0;
        try {
            for (int i = 0; i < concurrent; i++) {
                pool.scheduleAtFixedRate(() -> {
                    counter.incrementAndGet();//计数
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName()
                            + " is running...");

                }, 0, delay, TimeUnit.MILLISECONDS);
            }
            countDownLatch.await();
        } finally {
            // 关闭线程池
            pool.shutdownNow();
            end = System.currentTimeMillis();
            System.out.format("系统预计执行%d次，实际执行了%d次，耗时%d ms", count, counter.get(), (end - start));
        }
    }
}
