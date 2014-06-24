package com.dudo.t;

/**
 * User: dudu
 * Date: 14-1-13
 * Time: 下午4:09
 */
public class TestCloneable implements Cloneable{
    public static void main(String[] args) throws CloneNotSupportedException {
        TestCloneable testCloneable = new TestCloneable();
        testCloneable.clone();
    }
}
