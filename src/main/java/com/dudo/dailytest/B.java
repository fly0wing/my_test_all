package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-7
 * Time: 上午10:42
 */
public class B extends A{
    public static void main(String[] args) {

        new B().a();
        System.out.println(4);
    }
    public void a() {
        try{
            System.out.println(2);
            return;
        }finally {
            System.out.println(3);
        }
    }
}
