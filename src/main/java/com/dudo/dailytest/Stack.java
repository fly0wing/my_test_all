package com.dudo.dailytest;

import java.util.LinkedList;

/**
 * User: dudu
 * Date: 14-2-21
 * Time: 上午10:53
 */
public class Stack {
    public static void main(String[] args) throws Exception {
        Stack stack = new Stack();


        Thread tPOP = new Thread() {
            @Override
            public void run() {
                try {
                    stack.pop();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread tPush = new Thread() {
            @Override
            public void run() {
                stack.push("11");
            }
        };

        tPOP.start();
        tPush.start();
        tPush.join();
        tPOP.join();
    }
    LinkedList list = new LinkedList();

    public synchronized void push(Object x) {
        System.out.println("push 1");
        synchronized (list) {
            System.out.println("push 2");
            list.addLast(x);
            System.out.println("push 3");
            notify();
            System.out.println("push 4");
        }
    }

    public synchronized  Object pop() throws Exception {
        System.out.println("pop 1");
        synchronized (list) {
            System.out.println("pop 2");
            if (list.size() <= 0) {
                System.out.println("pop 3");
                list.wait();
                System.out.println("pop 4");
            }
            System.out.println("pop 5");
            return list.removeLast();
        }
    }
}