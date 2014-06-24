package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-30
 * Time: 上午9:46
 */

/*
 * 题目详情：
    输入一个整数n，求从1到n的这n个整数的十进制表示中，1出现的次数。
 输入：
    输入一个整数n，如：12。
 输出：
    输出n个整数的十进制表示中1出现的次数。如：1-12中，出现1，10，11，12，一共出现了5次1。
 */
public class CountOne {
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        Num n = new Num(1200);
        System.out.println(n.countOne());
        long b = System.currentTimeMillis();
        System.out.println(b-a);
        //641
//        22
    }
}
class Num {
    private long num;
    private StringBuffer numStr = new StringBuffer();

    Num(long a) {
        this.num = a;
    }

    private void concat() {
        if (this.num < 0) {
            return;
        }
        for (long i = this.num; i > 0; i--) {
            this.numStr.append(i);
        }
    }

    public int countOne() {
        concat();
        return this.numStr.toString().split("1",-1).length - 1;
    }
}
