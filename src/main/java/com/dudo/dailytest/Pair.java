package com.dudo.dailytest;

import java.util.Arrays;
import java.util.List;

/**
 * User: zk
 * Date: 13-7-6
 * Time: 上午12:30
 */
public class Pair<T> {
    private final T first;
    private final T second;
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    public T first() {
        return first;
    }
    public T second() {
        return second;
    }
    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first), String.valueOf(second));
    }
    public static void main(String[] args) {
//        Pair p = new Pair<Object> (23, "skidoo");
        Pair<Object> p = new Pair<Object> (23, "skidoo");
        System.out.println(p.first() + " " + p.second());
//        List<String> a = p.stringList();
        for (String s :  p.stringList())
            System.out.print(s + " ");
    }
}