package com.dudo.dailytest;

/**
 * Created with IntelliJ IDEA.
 * User: zk
 * Date: 13-6-26
 * Time: 上午9:09
 * To change this template use File | Settings | File Templates.
 */
public class Parent {
    public int addValue(int a, int b) {
        int s;
        s = a + b;
        return s;
    }
}

class Child extends Parent {
//    int addValue( int a, int b ){// do something...
//        return 0;
//     }
    public void addValue (){
    // do something...
    }
    public int addValue( int a ){
    // / do something...
        return 0;
    }
//    public int addValue( int a, int b )throws Exception {
//    //do something...
//        return 0;
//    }

}
