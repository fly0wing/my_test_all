package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-30
 * Time: 下午12:45
 */

public class LongDivision {
//    public static void main(String args[]) {
//        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
//        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
//        System.out.println(Long.MAX_VALUE/MILLIS_PER_DAY);
//        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
//        System.out.println(12345+5432l);
//
//    }


    public static void main(String[] args) {
        byte bytes[] = new byte[256];
        for (int i = 127; i < 256; i++)
            bytes[i] = (byte)i;
        String str = new String(new byte[]{-2});
        for (int i = 0, n = str.length(); i < n; i++){
            System.out.println(str.charAt(i));
            System.out.println((int)str.charAt(i) + " ");
        }
    }
}
