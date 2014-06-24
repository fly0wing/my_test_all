package com.dudo.dailytest;

import java.util.*;

/**
 * User: zk
 * Date: 13-8-9
 * Time: 上午10:02
 */
public class CollectionTest {
    public static void main(String[] args) {
        ArrayListTest();
        LinkedListTest();
    }

//    private static

    private static void ArrayListTest() {
        print("ArrayListTest start.");
        long a = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(10000);
        insertVal(arrayList);
        deleteOne(arrayList);
        randomInsertVal(arrayList);
        print(arrayList);
        long b = System.currentTimeMillis();
        print("ArrayListTest end." + (b - a));
    }

    private static void LinkedListTest() {
        print("LinkedListTest start.");
        long a = System.currentTimeMillis();
        LinkedList<Object> arrayList = new LinkedList<>();
        insertVal(arrayList);
        deleteOne(arrayList);
        randomInsertVal(arrayList);
        print(arrayList);
        long b = System.currentTimeMillis();
        print("LinkedListTest end." + (b - a));
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static void deleteOne(List list) {
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            if (i % 3 == 0) {
                it.remove();
             //   list.remove(i);
            }
            i++;
        }
    }

    private static void print(List list) {
        print("size:"+list.size());
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
    }

    private static void insertVal(List list) {
        Random random = new Random(100);
        for (int i = 0; i < 50000; i++) {
            list.add(i, i);
//            list.add(i);
        }
    }

    private static void randomInsertVal(List list) {
        Random random = new Random(100);
        for (int i = 0; i < 5; i++) {
            list.add(list.size() / 2, i);
//            list.add(i);
        }
    }

    private static int search(List l, Object obj) {
        return Collections.binarySearch(l, obj);
    }
}
