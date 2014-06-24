package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-11
 * Time: 下午6:32
 */
public class NumberRange {
    private volatile  int lower;
    private volatile  int upper;

    public static void main(String[] args) {
        NumberRange numberRange1 = new NumberRange();
        Theard1 t1 = new Theard1();
        Theard2 t2 = new Theard2();
        t1.setNumberRange(numberRange1);
        t2.setNumberRange(numberRange1);
        t1.start();
        t2.start();
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int value) {
        if (value > upper)
            throw new IllegalArgumentException("1");
        lower = value;
        System.out.println("setLower:"+value);
        System.out.println(lower+","+upper);
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int value) {
        if (value < lower)
            throw new IllegalArgumentException("2");
        upper = value;
        System.out.println("setUpper:"+value);
        System.out.println(lower+","+upper);
    }

}

class Theard1 extends Thread {
    private NumberRange numberRange;

    void setNumberRange(NumberRange numberRange) {
        this.numberRange = numberRange;
    }

    @Override
    public void run() {
        numberRange.setLower(5);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
class Theard2 extends Thread {
    private NumberRange numberRange;

    void setNumberRange(NumberRange numberRange) {
        this.numberRange = numberRange;
    }

    @Override
    public void run() {
        numberRange.setUpper(3);
    }
}