package com.dudo.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * User: zk
 * Date: 13-8-27
 * Time: 下午4:54
 */
public class BubbleSort implements MySortable {
    private static int[] arr =
            new int[]{1, 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 18, 19, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 40, 39};
    //                new int[]{40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int linenum = 1;

    public static void main(String[] args) {
        MySortable s = new BubbleSort();
        s.sort(arr);
    }

    @Override
    public void sort(int[] arr) {
        print(arr);
// 相邻找最小 交换
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            boolean hasSwap = false;
            //TODO 可优化.保存最后一次交换的idx,如果小于i则内层循环从i开始.
            for (int j = arr.length - 1; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    hasSwap = true;
                }
            }
            if (!hasSwap) {
                break;
            }
            print(arr);
        }
    }

    @Override
    public void print(int[] arr) {
        System.out.println(linenum++ + "\t" + Arrays.toString(arr));
    }
}
