package com.dudo.dailytest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;

/**
 * User: zk
 * Date: 13-8-7
 * Time: 下午2:35
 */
public class ThreadTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor a = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2);
//        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//        threadPoolExecutor.
        Collections.synchronizedList(new ArrayList<String>(500));

    }
}
