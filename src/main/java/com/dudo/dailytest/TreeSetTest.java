package com.dudo.dailytest;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User: zk
 * Date: 13-8-12
 * Time: 上午9:33
 */
public class TreeSetTest {
    public static void main(String[] args) {
        System.out.println(1 << 30);
        TreeSet<Integer> treeSet=new TreeSet<Integer>();
        treeSet.add(100);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(10000);
        SortedSet<Integer> sub = new TreeSet();
        sub=treeSet.subSet(5,200);
        System.out.println(treeSet);
        System.out.println(sub);
        System.out.println(sub.add(110));
        System.out.println(treeSet);
        System.out.println(sub);
        System.out.println(treeSet.ceiling(1));

    }
}
