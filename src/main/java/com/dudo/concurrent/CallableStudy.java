package com.dudo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zkai.zhang on 2014/6/9.
 */
public class CallableStudy {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future submit = executorService.submit(a);

        B b = new B();
        b.run();

        C c = new C();
        c.start();
        c.sleep(1,1);
    }
}

class C extends Thread {

}
class B implements Runnable {

    @Override
    public void run() {

    }
}
class A implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("aaa");
        return null;
    }
}
