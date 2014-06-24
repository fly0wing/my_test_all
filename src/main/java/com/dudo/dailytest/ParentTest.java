package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-13
 * Time: 上午8:58
 */
public class ParentTest {
    protected void test() {}

    public ParentTest()
    {
        this.test();
    }

    public static void main(String[] args)
    {
        new ChildTest();
    }



}
class ChildTest extends ParentTest
{
    private int instanceValue = 1;
    public void test()
    {
        System.out.println(instanceValue);
    }
}