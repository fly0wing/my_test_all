package com.dudo.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: dudu
 * Date: 14-1-15
 * Time: 下午1:11
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 t = new Test1();
        t.t1();
    }
    public void t1() {
        Runnable r = () -> {
            System.out.println(1);
            System.out.println(this);
            System.out.println("hello world");
        };
        r.run();
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
                System.out.println(this);
            }
        };
        a.run();

        Comparator<String> c = (String s1, String s2) ->
                s2.length() - s1.length();
        Comparator<String> c2 = (String s1, String s2) -> {
            System.out.println(3);
            System.out.println(this);
            return s2.length() - s1.length();
        };
        Comparator<String> c3 = new
                Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        System.out.println(4);
                        System.out.println(this);
                        return 0;
                    }
                };
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");
        Collections.sort(list,c);
        Collections.sort(list,c2);
        Collections.sort(list,c3);
    }
}
