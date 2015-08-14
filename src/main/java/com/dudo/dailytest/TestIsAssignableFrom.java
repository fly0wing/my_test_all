package com.dudo.dailytest;

public class TestIsAssignableFrom {

    public static void main(String args[]) {
        int[] a = new int[1];
        Integer[] b = new Integer[1];
        System.out.println(Object.class.isAssignableFrom(a.getClass()));
        System.out.println(Object[].class.isAssignableFrom(a.getClass()));

        System.out.println(Object.class.isAssignableFrom(b.getClass()));
        System.out.println(Object[].class.isAssignableFrom(b.getClass()));
    }
}
