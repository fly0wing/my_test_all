package com.dudo.dailytest;

/**
 * User: dudu
 * Date: 14-2-21
 * Time: 上午10:05
 */
public class MethodOverrideVsOverload {
    @Override
    public boolean equals(Object other) {
        System.out.println("A1");
        return true;
    }
    public boolean equals( MethodOverrideVsOverload other ) {
        System.out.println("A");
        return true;
    }
    public static void main(String[] args) {
        Object o1 = new MethodOverrideVsOverload();
        Object o2 = new MethodOverrideVsOverload();
        MethodOverrideVsOverload o3 = new MethodOverrideVsOverload();
        MethodOverrideVsOverload o4 = new MethodOverrideVsOverload();
        if(o1.equals(o2)){
            System.out.println("B");
        }
        if(o3.equals(o4)){
            System.out.println("C");
        }
    }
}
