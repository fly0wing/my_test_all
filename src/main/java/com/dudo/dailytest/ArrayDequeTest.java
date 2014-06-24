package com.dudo.dailytest;

import java.util.ArrayDeque;

/**
 * User: zk
 * Date: 13-8-16
 * Time: 下午3:44
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
//        int a_14 =0b1111_1111_1111_1111_1111_1111_1111_0010;
//        System.out.println(a_14);

        System.out.println(-14&31);
        ArrayDeque deque =new ArrayDeque();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        deque.addLast(1);
        deque.addLast(1);
        deque.addLast(1);
        deque.addLast(1);
        System.out.println(deque.size());
        deque.addLast(1);
        System.out.println(deque.size());
        deque.addFirst(1);
        System.out.println(deque.size());
        deque.getLast();
        deque.removeFirstOccurrence(1);
//        deque.clear();
    }
    private static void init(){
        int a= 12;
//        Long initialCapacity= Long.MAX_VALUE-(Long.MAX_VALUE>>>1+1);
//        Integer initialCapacity= Integer.MAX_VALUE-(Integer.MAX_VALUE>>>1+1);
//        long initialCapacity = 0B0100000000000000000000000000000000000000000000000000000000000000L;
        int initialCapacity = 0B01000000000000000000000000000000;

//        System.out.println(a>>>1);
//        System.out.println(a>>>2);
//        System.out.println(a>>>4);
//        System.out.println(a>>>8);
//        System.out.println(a>>>16);

//        int initialCapacity =0;
        System.out.println(initialCapacity);
        // 产生一个最大值. 按位或运算.32位 或 64位 全为1
        initialCapacity |= (initialCapacity >>>  1);
        initialCapacity |= (initialCapacity >>>  2);
        initialCapacity |= (initialCapacity >>>  4);
        initialCapacity |= (initialCapacity >>>  8);
        initialCapacity |= (initialCapacity >>> 16);//31位1 Integer.MAX_VALUE
//        initialCapacity |= (initialCapacity >>> 32);//63为1 Long.MAX_VALUE
        System.out.println("````````````");
        initialCapacity++;
        if (initialCapacity < 0)   // Too many elements, must back off
            initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        System.out.println(initialCapacity);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
