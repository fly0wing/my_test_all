package com.dudo.jvmtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * User: zk
 * Date: 13-8-14
 * Time: 下午6:37
 */
public class BTraceTest {
    public int add(int a, int b){
        return a+b;
    }
    public static void main(String[] args )   throws  Exception  {
        BTraceTest t = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<10;i++){
            reader.readLine();
            int a = (int) Math.round(Math.random()*100) ;
            int b = (int) Math.round(Math.random()*100) ;
            System.out.println(t.add(a,b) );
        }
    }
}
