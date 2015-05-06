package com.dudo.atomicexample;

/**
 * Created by zhangkai9 on 2015/5/5.
 */
public class Book {

    private String name;

    public Book()
    {
    }

    public Book( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
