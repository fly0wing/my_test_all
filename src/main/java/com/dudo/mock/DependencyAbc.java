package com.dudo.mock;

/**
 * @author zhangkai9
 * @date 2015/6/30
 */
public class DependencyAbc {

    public int intReturningMethod() {
        return 0;
    }

    public String stringReturningMethod() throws SomeCheckedException{
        return "abc__abc";
    }
}
