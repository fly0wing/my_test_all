package com.dudo.jvmtest;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * java 堆溢出 堆大小区间(-Xms,-Xmx)
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * java.lang.OutOfMemoryError: Java heap space
 *
 * User: zk
 * Date: 13-8-14
 * Time: 上午9:46
 *
 */
public class HeapOOM {
    public static void main(String[] args) {
//        strongReferenceTest();  //4921 :540217
//        softReferenceTest();    //2847 :240097
        weakReferenceTest();    //10964:340573
        weakReferenceTest1();   //11059:340611
//        List<Reference<OOMObject>> list=new ArrayList<>();
//        while (true){
//            list.add(new SoftReference(new OOMObject()));
//            System.gc();
//        }
    }

    private static void strongReferenceTest() {
        List<OOMObject> list = new ArrayList<>();
        int i = 0;
        long a = System.currentTimeMillis();
        while (true) {
            i++;
            list.add(new OOMObject());
            if (i % 100000 == 0)
                System.gc();

            long b = System.currentTimeMillis();
            System.out.println(b - a + ":" + i);
        }
    }

    private static void weakReferenceTest() {
        List<WeakReference<OOMObject>> list = new ArrayList<>();
        int i = 0;
        long a = System.currentTimeMillis();
        while (true) {
            i++;
            list.add(new WeakReference(new OOMObject()));
            if (i % 100000 == 0)
                System.gc();

            long b = System.currentTimeMillis();
            System.out.println(b - a + ":" + i);
        }
    }
    private static void weakReferenceTest1() {
        List<WeakReference<Object>> list = new ArrayList<>();
        int i = 0;
        long a = System.currentTimeMillis();
        while (true) {
            i++;
            list.add(new WeakReference(1));
            if (i % 100000 == 0)
                System.gc();

            long b = System.currentTimeMillis();
            System.out.println(b - a + ":" + i);
        }
    }
    private static void softReferenceTest() {
        List<SoftReference<OOMObject>> list = new ArrayList<>();
        int i = 0;
        long a = System.currentTimeMillis();
        while (true) {
            i++;
            list.add(new SoftReference(new OOMObject()));
            if (i % 100000 == 0)
                System.gc();

            long b = System.currentTimeMillis();
            System.out.println(b - a + ":" + i);
        }
    }

    static class OOMObject {
    }
}
