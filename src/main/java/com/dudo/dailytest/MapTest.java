package com.dudo.dailytest;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * User: zk
 * Date: 13-8-12
 * Time: 上午10:52
 */
public class MapTest {
    public static void main(String[] args) {
        hashMapTest();
//        treeMapTest();
    }

    private static void treeMapTest() {
        TreeMap treeMap = new TreeMap();
        treeMap.put("01",1);
        treeMap.put("100",1);
        treeMap.put(".1",1);
        treeMap.put("10",1);
        treeMap.put("-1",1);
        treeMap.put("1000",1);

        System.out.println(treeMap);
    }

    private static void hashMapTest() {
        HashMap hashMap =new HashMap();
        hashMap.put("1",2);
        hashMap.put("22",2);
        hashMap.put("3",2);
        hashMap.put("4",2);
        hashMap.put("5",2);
        hashMap.put("6",2);
        System.out.println("2".hashCode());
        System.out.println("2".hashCode());
        //{22=2, 3=2, 6=2, 5=2, 1=2, 4=2}
        //{22=2, 3=2, 1=2, 6=2, 5=2, 4=2}
        System.out.println(hashMap);
    }
}
