package com.dudo.dailytest;

/**
 * User: dudu
 * Date: 14-2-21
 * Time: 上午10:49
 */
public class Z {
    public static void main(String[] args) {
        new Z();
    }
    Z() {
        Z alias1 = this;
        Z alias2 = this;
        synchronized(alias1) {
            try {
                alias2.wait();
                System.out.println("DONE WAITING");
            } catch (InterruptedException e) {
                System.out.println("INTERR UPTED");
            } catch (Exception e) {
                System.out.println("OTHER EXCEPTION");
            } finally {
                System.out.println("FINALLY");
            }
        }
        System.out.println("ALL DONE");
    }
}
