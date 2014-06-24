package com.dudo.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * User: zk
 * Date: 13-8-27
 * Time: 下午4:04
 */
public class InsertionSort implements MySortable {
    private static int[] arr =
            new int[]{1, 38, 37, 36, 35, 27, 26, 25, 24, 30, 29, 28, 17, 16, 15, 14, 13, 12, 23, 22, 21, 20, 18, 19, 34, 33, 32, 31, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 40, 39};
    //                new int[]{40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int linenum = 1;

    public static void main(String[] args) {
        MySortable s = new InsertionSort();
        s.sort(arr);
    }

    @Override
    public void sort(int[] arr) {
        print(arr);
        int temp;
        for (int i = 1; i < arr.length; i++) {
            // arr[i]与之前的arr[j]元素比较最小, 将j之后的元素 向后移一位,将i出入到中间.
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    System.arraycopy(arr, j, arr, j + 1, i - j);
                    arr[j] = temp;
                    print(arr);
                    break;
                }
            }
        }
    }

    public void print(int[] arr) {
        System.out.println(linenum++ + "\t" + Arrays.toString(arr));
    }
}
