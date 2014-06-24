package com.dudo.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * User: zk
 * Date: 13-8-27
 * Time: 下午5:19
 */
public class ShellSort implements MySortable {
    private static int[] arr =
            new int[]{1, 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 18, 19, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 40, 39};
    //                new int[]{40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int linenum = 1;

    public static void main(String[] args) {
        MySortable s = new ShellSort();
        s.sort(arr);
    }

    @Override
    public void sort(int[] arr) {
        //分而治之.数组 [1, 2, 3, 4, 5, 6, 7, 8] ，如果以 gap = 2 来划分，可以分为 [1, 3, 5, 7] 和 [2, 4, 6, 8] 两个数组
        //然后分别对划分出来的数组进行插入排序，待各个子数组排序完毕之后再减小 gap 值重复进行之前的步骤，
        // 直至 gap = 1 ，即对整个数组进行插入排序，此时的数组已经基本上快排好序了
        print(arr);
        int len = arr.length;
        int gap = (int) Math.ceil(len/ 2);
        while (gap > 0) {
            System.out.println(gap + ":");
            for (int i = 0; i < len/ gap; i++) {
                for (int j = i; j <0 ; j++) {
                    if (arr[len % gap] > arr[len % gap * i]) {

                    }
                }
                print(arr);
            }
            gap = (int) Math.ceil(gap / 2);
        }
    }

    @Override
    public void print(int[] arr) {
        System.out.println(linenum++ + "\t" + Arrays.toString(arr));
    }
}
