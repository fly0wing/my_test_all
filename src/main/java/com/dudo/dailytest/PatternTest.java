package com.dudo.dailytest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zkai.zhang on 2014/6/10.
 */
public class PatternTest {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\p{ASCII}");//所有 ASCII：[\x00-\x7F]
        Pattern p2 = Pattern.compile("\\p{Cntrl}");//控制字符：[\x00-\x1F\x7F]
        char a = '\u0000';
        String s = a + "";
        Matcher m = p.matcher(s);
        Matcher m2 = p2.matcher(s);
        boolean b = m.matches();
        boolean b2 = m2.matches();
        System.out.println(a);
        System.out.println(b);
        System.out.println(b2);

        // ===================================
        a = '\u0030';//数字:0
        s = a + "";
        m = p.matcher(s);
        m2 = p2.matcher(s);
        b = m.matches();
        b2 = m2.matches();
        System.out.println(a);
        System.out.println(b);
        System.out.println(b2);
    }
}
