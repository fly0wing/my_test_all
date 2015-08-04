package com.dudo.aop;

/**
 * @author zhangkai9
 * @date 2015/7/21
 */
public class ServiceA {
    @Loggable
    public void s1() {
        System.out.println("111");
    }
}
