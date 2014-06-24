package com.dudo.dailytest;

/**
 * User: dudu
 * Date: 14-2-21
 * Time: 上午10:13
 */
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            throw new ExceptionB();
        } catch (ExceptionA exceptionA) {
            System.out.println("ExampleA");
        } catch (Exception exception) {
            System.out.println("Example");
        }
    }
}
class ExceptionA extends Exception{

}
class ExceptionB extends ExceptionA{

}
