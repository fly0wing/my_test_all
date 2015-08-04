package com.dudo.mock;

/**
 * @author zhangkai9
 * @date 2015/6/30
 */
public class UnitUnderTest {
    private final DependencyAbc abc = new DependencyAbc();

    public void doSomething() {
        int n = abc.intReturningMethod();

        for (int i = 0; i < n; i++) {
            String s;

            try {
                s = abc.stringReturningMethod();
            } catch (SomeCheckedException e) {
                // somehow handle the exception
            }

            // do some other stuff
        }
    }
}
