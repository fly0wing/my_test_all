package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 上午9:26
 */
public class CountOne2 {
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(countOne(320));
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }

    /*
    * 对于整数N，其每位上的数字从高到低可以表示为 Nn Nn-1 ... N0，其中Nn 大于0
    * 0 ... n对应于10的相应位的10的幂次数
    * 对于指定的N，1-N中出现的次数看1 - Nn * 10^n中出现次数(count(Nn)) 与
    * 余数Nn-1...N0中出现次数(count(Nn-1..N0))之和
    */
    public static int countOne(int n) {
        if (n == 0)
            return 0;
        int count = 0;
        int digs = (int) Math.log10(n);
        int tens = (int) Math.pow(10, digs);
        int highest = n / tens;
        int rem = n % tens;
        int remCount = 0;
        //计算每个整Nn时 1出现次数
        for (int i = 0; i < digs; i++) {
            remCount = remCount * 10 + (int) Math.pow(10, i);
        }
        //整Nn个数 + 1为最高位的次数
        if (highest > 1) {
            count = remCount * highest + tens;
        } else if (highest == 1) {
            count = remCount + (rem + 1);
        }
        //余数/低位部分计数
        count += countOne(rem);
        return count;
    }
}
