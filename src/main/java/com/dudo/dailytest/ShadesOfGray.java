package com.dudo.dailytest;

/**
 * 可以证明，在这样的上下文环境中，有一条规则决定着程序的行为，
 * 即当一个变量和一个类型具有相同的名字，并且它们位于相同的作用域时，
 * 变量名具有优先权[JLS 6.5.2]。变量名将遮掩（obscure）类型名[JLS 6.3.2]。
 * 相似地，变量名和类型名可以遮掩包名。这条规则真的是相当地晦涩，
 * 任何依赖于它的程序都极有可能使它的读者晕头转向。
 *
 *
 * User: ZK
 * Date: 13-5-15
 * Time: 上午11:33
 */
public class ShadesOfGray {
    public static void main(String[] args){
        System.out.println(X.Y.Z);
    }
}

class X {
    static class Y {
        static String Z = "Black";
    }
    static C Y = new C();
}

class C {
    String Z = "White";
}