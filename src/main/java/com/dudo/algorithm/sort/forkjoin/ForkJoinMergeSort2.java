package com.dudo.algorithm.sort.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zkai.zhang on 2014/6/9.
 */
public class ForkJoinMergeSort2 {

    protected Integer compute(int num) throws InterruptedException {
        if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        } else if (num == 3) {
            return 4;
        }
        Thread.sleep(1);
//        Thread.sleep(2);
        return compute(num - 1) + compute(num - 2);
    }

    public static void main(String[] args) throws InterruptedException {
        long l1 = System.currentTimeMillis();
        ForkJoinMergeSort2 sort = new ForkJoinMergeSort2();
        Integer compute = sort.compute(30);
//        Integer compute = sort.compute(25);
//        Integer compute = sort.compute(5);
        System.out.println(compute);
        long l2 = System.currentTimeMillis();
        System.out.print(l2-l1);
//        866988874
//        1875

//        13530
//        4271

//        13530
//        6012
//        13530
//        8580
    }

}
