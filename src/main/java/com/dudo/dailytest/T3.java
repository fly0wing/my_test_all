package com.dudo.dailytest;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Think
 * Date: 13-9-12
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class T3 {
    public static void main(String[] args) {
        String a = "<javaScriptasd>asd<asd>";

        Random r = new Random();
        boolean b  =r.nextBoolean();
        System.out.println( strFilter(a));
    }
    static String  strFilter(String str){
        if(str==null) return null;
        return  str.replace("<","").replace(">","");
    }
}
