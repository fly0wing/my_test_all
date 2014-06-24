package com.dudo.designpattern;

/**
 * User: dudu
 * Date: 14-1-13
 * Time: 下午5:44
 */
public class Singleton3 {
    private Singleton3() {
        System.out.println(this.getClass().getName() + " is create!");
    }

    //不需要使用synchronized
    public static Singleton3 getInstance() {
        return SingletonHolder.instance;
    }

    // 内部类 保证主类加载到JVM中时, 内部类不被加载.
    private static class SingletonHolder {
        //static实例 保证并发环境下的安全创建
        private static Singleton3 instance = new Singleton3();
    }
}
