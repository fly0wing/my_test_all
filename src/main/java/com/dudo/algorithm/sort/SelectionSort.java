package com.dudo.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度和空间复杂度分别为 O(n2 ) 和 O(1)
 * <p/>
 * User: zk
 * Date: 13-8-27
 * Time: 下午3:34
 */
public class SelectionSort implements MySortable {
    private static int[] arr =
            new int[]{1, 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 18, 19, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 40, 39};
    //          new int[]{ 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 19, 18, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,40, 39};
//            new int[]{40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int linenum = 1;

    public static void main(String[] args) {
        MySortable s = new SelectionSort();
        s.sort(arr);
    }

    @Override
    public void sort(int[] arr) {
        print(arr);
        int minIdx;// 最小的数据下标
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIdx = i;
            // 先找出最小
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) minIdx = j;
            }
            // 再交换
            if (minIdx > 0) {
                temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
            }
            print(arr);
        }
    }

    @Override
    public void print(int[] arr) {
        System.out.println(linenum++ + "\t" + Arrays.toString(arr));
    }
}
