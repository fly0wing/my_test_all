package com.dudo.jvmtest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 * vm args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * java.lang.OutOfMemoryError
 * User: zk
 * Date: 13-8-14
 * Time: 下午3:42
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int i = 0;
        while (true) {
            unsafe.allocateMemory(_1MB);
            System.out.println(++i);
        }
    }
}
