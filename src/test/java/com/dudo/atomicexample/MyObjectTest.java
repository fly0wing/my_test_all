package com.dudo.atomicexample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangkai9 on 2015/5/5.
 */
public class MyObjectTest {
    private MyObject obj;

    @Before
    public void setUp()
    {
        obj = new MyObject();
        obj.setWhatImReading( new Book( "Java 2 From Scratch" ) );
    }

    @Test
    public void testUpdate()
    {
        obj.setWhatImReading( new Book(
                "Pro Java EE 5 Performance Management and Optimization" ) );


        Assert.assertEquals("Incorrect book name",
                "Pro Java EE 5 Performance Management and Optimization",
                obj.getWhatImReading().getName());
    }

}