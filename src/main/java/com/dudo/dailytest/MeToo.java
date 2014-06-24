package com.dudo.dailytest;

import java.io.File;
import java.util.regex.Matcher;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 上午10:33
 */
public class MeToo {
    public static void main(String[] args) {
        System.out.println(MeToo.class.getName().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");
        System.out.println(MeToo.class.getName().replaceAll(".", "/") + ".class");
        System.out.println("a3ba1".replaceAll("(\\d)","\\\\"));
    }
}
