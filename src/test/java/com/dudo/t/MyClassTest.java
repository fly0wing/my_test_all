package com.dudo.t;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Think
 * Date: 13-9-30
 * Time: 下午2:00
 */
public class MyClassTest {
    MyClass mc;

    @Before
    public void setUp() throws Exception {
        mc = new MyClass();
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals("ceshi-ing",36.,mc.add(Double.valueOf(2),Double.valueOf("33")));
//        Assert.assertEquals("ceshi-ing",35.,mc.add(Double.valueOf(-2),Double.valueOf("33")));
    }
}
