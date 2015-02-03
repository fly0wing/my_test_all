package com.dudo.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zkai on 2014/8/31.
 */
public class ThreadLocalTest {
    static final ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return false;
        }
    };
    private final Executor executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        // 1. 测试场景.
        // 主线程类有个ThreadLocal实例,(final ThreadLocal, 非static),set一个值.
        // 用线程池调用该实例,(Executor)
        // 那么,线程池是否能拿到主线程的实例中set的值.
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.booleanThreadLocal.set(true);
        threadLocalTest.executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocalTest.booleanThreadLocal.get());
                System.out.println(Thread.currentThread());
            }
        });
    }
}
