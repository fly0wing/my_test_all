package com.dudo.algorithm.sort.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zkai.zhang on 2014/6/9.
 */
public class ForkJoinMergeSort3 extends RecursiveTask<Integer> {
    private int num;
    public static volatile AtomicLong result = new AtomicLong(0);

    public ForkJoinMergeSort3(int num) {
        this.num = num;
    }

    @Override
    protected Integer compute() {
        try {
//            Thread.sleep(2);
            Thread.sleep(1);
            // Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (num == 1) {
//            result.addAndGet(1);
            return 1;
        } else if (num == 2) {
//            result.addAndGet(2);
            return 2;
        } else if (num == 3) {
//            result.addAndGet(4);
            return 4;
        }
        ForkJoinMergeSort3 f1 = new ForkJoinMergeSort3(num - 1);
        ForkJoinMergeSort3 f2 = new ForkJoinMergeSort3(num - 2);
        f1.fork();
        return f2.compute()+f1.join();
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();

//        ForkJoinMergeSort3 sort = new ForkJoinMergeSort3(5);
//        ForkJoinMergeSort3 sort = new ForkJoinMergeSort3(25);
        ForkJoinMergeSort3 sort = new ForkJoinMergeSort3(30);
        // sort.compute();
        // // 1664080
        // // 1664080
        // // 448
        // System.out.println(sort.result.get());
        long l2 = System.currentTimeMillis();
        // System.out.println(l2 - l1);

        l1 = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(3);
        // ForkJoinPool pool = new ForkJoinPool(3);
        System.out.println(pool.invoke(sort));
        l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        // 866988874 43
        // 16234

        // 150050 25 2
        // 64196

        // 150050
        // 94562
//        150050
//        64869
    }

    public static int calc(int num) {
        if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        } else if (num == 3) {
            return 4;
        }
        return calc(num - 1) + calc(num - 2);
    }

    private void computeDo(int[] arr, int start, int mid, int end) {

    }

    public void print(int[] arr) {
        System.out.println("\t" + Arrays.toString(arr));

    }
}
