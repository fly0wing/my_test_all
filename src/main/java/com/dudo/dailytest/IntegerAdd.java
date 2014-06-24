package com.dudo.dailytest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: dudu
 * Date: 14-2-8
 * Time: 上午10:02
 */
public class IntegerAdd {
    public static void main(String[] args) {
//        a();
//        b();
//        c();
        List abc = new ArrayList<>();
        abc.add("1");
        a1(abc);
    }

    private static void a() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println(sum);
    }

    private static void b() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE - 1;
        int sum = a + b ;
          sum += 1;
        System.out.println(sum);
    }

    private static void c() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE - 1;
        int sum = a + 1 ;
//        sum +=  b;
        System.out.println(sum);
        System.out.println(b);

    }
    private static  <T extends Runnable&Comparable & Serializable> void a1(List<T > a){
        System.out.print(a);
    }

//    private static  List<? extends T> void a2(List<? extends T> a){
//        System.out.print(a);
//    }
}
