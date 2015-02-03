package com.dudo.dailytest;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zkai on 2014/12/3.
 */
public class PrimarySchoolTopic {
    private class Nums implements Comparable {
        private int a;
        private int b;

        public Nums(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public boolean verify() {
            return verifyNum(a) && verifyNum(b) && a != b && a + b < maxSum;
        }

        private boolean verifyNum(int i) {
            return i >= min && i <= max;
        }

        @Override
        public int hashCode() {
            return getStr().hashCode();
        }

        private String getStr() {
            String format;
            if (a > b) {
                format = String.format("%s+%s=%s", b, a, a + b);
            } else {
                format = String.format("%s+%s=%s", a, b, a + b);
            }
            return format;
        }

        @Override
        public String toString() {
            return getStr();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Nums)) {
                return false;
            }
            Nums two = (Nums) obj;
            return (this.a == two.a && this.b == two.b)
                    || (this.a == two.b && this.b == two.a);
        }

        // case 2
        @Override
        public int compareTo(Object obj) {
            if (obj == null || !(obj instanceof Nums)) {
                return -1;
            }
            Nums two = (Nums) obj;
            return two.a == a ?
                    two.b == b ?
                            0 :
                            two.b - b :
                    two.a - a;
        }
    }

    public static void main(String[] args) {
        PrimarySchoolTopic topic = new PrimarySchoolTopic(1, 99, 100);
        topic.compute().print();
    }

    private int min;
    private int max;
    private int maxSum;
    Set<Nums> numCount = new HashSet<>(); // case1 需要实现hashCode().equals().
//    Set<Nums> numCount = new TreeSet<>(); // case2 需要实现 Comparable.compareTo() [或在构造时传入 Comparator]
//    Set<Nums> numCount = new CopyOnWriteArraySet<>(); // case3 仅需要 equals().

    public PrimarySchoolTopic(int min, int max, int maxSum) {
        this.min = min;
        this.max = max;
        this.maxSum = maxSum;
    }

    public PrimarySchoolTopic compute() {
        for (int i = min; i <= max; i++) {
            for (int j = min; j <= max; j++) {
                Nums nums = new Nums(i, j);
                if (nums.verify()) {
                    numCount.add(nums);
                }
            }
        }
        return this;
    }

    public int size() {
        return numCount.size();
    }

    public PrimarySchoolTopic print() {
        numCount.forEach(System.out::println);
        System.out.println(numCount.size());
        return this;
    }
}
