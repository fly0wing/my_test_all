package com.dudo.t;

/**
 * User: Think
 * Date: 13-9-30
 * Time: 下午1:58
 */
public class MyClass {
    public static double add(Double a, Double b) {
        if (a < 0) {
            return -a + b;
        } else {
            a=a+1;
        }
        return a + b;
    }
}
