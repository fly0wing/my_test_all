package com.dudo.designpattern;

/**
 * User: dudu
 * Date: 14-1-13
 * Time: 下午5:37
 */
public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2() {
        System.out.println(this.getClass().getName() + " is create!");
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
