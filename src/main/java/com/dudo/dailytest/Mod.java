package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 下午1:58
 */
public class Mod {
    public static void main(String[ ] args) {
        final int MODULUS = 3;
        int[] histogram = new int[MODULUS];
// Iterate over all ints
        int i = Integer.MIN_VALUE;
        do {
            histogram[Math.abs(i) % MODULUS]++;
        } while (i++ != Integer.MAX_VALUE);
        for (int j = 0; j < MODULUS; j++)
            System.out.println(histogram[j] + " ");
    }
}
