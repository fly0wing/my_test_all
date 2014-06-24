package com.dudo.concurrent;

import java.util.concurrent.*;

/**
 * User: dudu
 * Date: 14-1-17
 * Time: 上午9:45
 */
public class Test {
    public static void main(String[] args) {
//        t1();
        System.out.println(System.currentTimeMillis() + " " + Test.class.getName() + "~~~~~~~~");
        t2();
        System.out.println(System.currentTimeMillis() + " " + Test.class.getName() + "~~~~~~~~");

    }

    static void t2() {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
//        ThreadPoolExecutor threadPoolExecutor  = null;
        if (executorService instanceof ThreadPoolExecutor) {
            executorService = (ThreadPoolExecutor) executorService;
            ((ThreadPoolExecutor) executorService)
                    .setRejectedExecutionHandler(
                            new ThreadPoolExecutor.DiscardOldestPolicy()
//                            new ThreadPoolExecutor.AbortPolicy();
//                            new ThreadPoolExecutor.CallerRunsPolicy()
//                            new ThreadPoolExecutor.DiscardPolicy()
                    );
        }
        executorService.submit(new MyThread1());
        executorService.submit(new MyThread1());
        try {
            executorService.awaitTermination(1000, TimeUnit.NANOSECONDS);
            System.out.println("1");
//            executorService.shutdown();
//            executorService.shutdownNow();
//            executorService.invokeAny()
//            executorService.invokeAll();
            System.out.println("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.submit(new MyThread1());

    }

    static void t1() {
        Callable<String> callable = Executors.callable(new MyThread1(), "test");

        Object call = null;
        try {
            call = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(call);

    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(System.currentTimeMillis() + " " + this.getName() + "~~~~~~~~");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + "~~~~~~~~");
    }
}
