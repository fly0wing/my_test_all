package com.dudo.dailytest;

import java.util.Random;

/**
 * Created by zhangkai9 on 2015/5/5.
 */
public class NumberTest {
    public static void main(String[] args) {
        System.out.println(5 % -2 / 5.);
//        System.out.println(Integer.toBinaryString(Integer.parseInt("8001",16)));
//        test3();
//        test2();

//        test1();

    }

    private static void test3() {
        int x = Integer.parseInt("100 0000 0000 0000 0001".replaceAll(" ",""),2);
        x ^= x << 13;
        x ^= x >>> 17;
        x ^= x << 5;
        System.out.format("x :%s,x:%d,x& 0x8001:%d", Integer.toBinaryString(x),x,x& 0x8001
        );
    }
    private static void test2() {
        int x = Integer.parseInt("100 0000 0000 0000 0001".replaceAll(" ",""),2);
        int i1 = x << 13;
        int x2 = x^i1;
        int i2 = x2 >>> 17;
        int x3 = x2 ^ i2;
        System.out.format("x :%s,\r\ni1:%s,\r\nx2:%s,\r\ni2:%s,\nx3:%s", Integer.toBinaryString(x),
                Integer.toBinaryString(i1), Integer.toBinaryString(x2),
                Integer.toBinaryString(i2), Integer.toBinaryString(x3)
        );
    }

    private static void test1() {
        Random seedGenerator = new Random();
        for (int i = 0; i < 10000; i++) {
            int xx = seedGenerator.nextInt();
            int x = xx | 0x0100;
            int i1 = x << 13;
            x ^= i1;
            x ^= x >>> 17;
//            x ^= x << 5;
            int level=0;
            while (((x >>>= 1) & 1) != 0) ++level;
            System.out.println("xx:" + xx + "\ti1:"+i1+"\tx:" + x + "\t\txy:" + (x & 0x8001) + "\tlvl:" + level);
            System.out.format("x :%s,\ni1:%s,\r\nx2:%s", Integer.toBinaryString(x),
                    Integer.toBinaryString(i1), Integer.toBinaryString(x));

        }
    }
}
