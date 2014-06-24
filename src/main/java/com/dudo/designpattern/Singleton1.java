package com.dudo.designpattern;

/**
 * User: dudu
 * Date: 14-1-13
 * Time: 下午5:20
 */
public class Singleton1 {
    // static实例 保证并发环境下的安全创建
    private static Singleton1 instance = new Singleton1();

    // private构造方法 保证该类不会被其他系统构造
    private Singleton1() {
        System.out.println(this.getClass().getName() + " is create!");
    }

    public static void main(String[] args) {
        Singleton1.getInstance();
    }

    public static Singleton1 getInstance() {
        System.out.println("Singleton1.getInstance is called!");
        return instance;
    }
}
