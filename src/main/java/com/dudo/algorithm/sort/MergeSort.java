package com.dudo.algorithm.sort;

import java.util.Arrays;

/**
 * User: zk
 * Date: 13-8-27
 * Time: 下午5:54
 */
public class MergeSort implements MySortable {
    private static int[] arr =
            new int[]{1, 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 18, 19, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 40, 39};
    //                new int[]{40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int linenum = 1;

    public static void main(String[] args) {
        MySortable s = new MergeSort();
        s.sort(arr);
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private void sort(int[] arr, int start, int end) {
        print(arr);
        if (start < end) {
            int mid = (start + end) / 2;
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private void merge(int[] arr, int start, int mid, int end) {
        int i1 = start;
        int i2 = mid;
        int temp ;
        while (i1 < mid && i2 <= end) {
            if (arr[i1] > arr[i2]) {

            } else {

            }

            print(arr);
        }
    }

    @Override
    public void print(int[] arr) {
        System.out.println(linenum++ + "\t" + Arrays.toString(arr));

    }
}
