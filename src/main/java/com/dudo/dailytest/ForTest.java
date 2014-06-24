package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-14
 * Time: 上午9:26
 */
public class ForTest {
    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    public static void main(String[] argv) {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }

    }
}
