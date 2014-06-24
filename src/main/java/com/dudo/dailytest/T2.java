package com.dudo.dailytest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: zk
 * Date: 13-8-28
 * Time: 下午2:55
 */
public class T2 implements AutoCloseable, Appendable {
  private static   String a;

    public static String getA() {
        return a;
    }

    public static void setA(String a) {
        T2.a = a;
    }

    public static void main(String[] args) {
        Byte a = 1;
        setA("a");
        System.out.println(a.equals(1));
        Double d = Double.parseDouble("000603250");
        ;
        if (true) {
            System.out.printf("asdaasd%s%n", d+1*2+1);
        }
        System.out.println(d / 100);

        BigDecimal bigDecimal = new BigDecimal("000603250");
        BigDecimal bigDecimal100 = new BigDecimal("100");
        double dAmount = bigDecimal.divide(bigDecimal100, 2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println(dAmount);
    }

    @SuppressWarnings("UnusedParameters")
    private short a(String a) {
        return 0;
    }

    @Override
    public void close() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Appendable append(char c) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
