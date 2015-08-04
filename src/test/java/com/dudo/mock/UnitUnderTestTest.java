package com.dudo.mock;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author zhangkai9
 * @date 2015/6/30
 */
@RunWith(JMockit.class)
@Ignore
public class UnitUnderTestTest {

    @Test
    public void doSomethingHandlesSomeCheckedException(@Mocked final DependencyAbc abc) throws Exception {
        new Expectations() {{
            new DependencyAbc();

            abc.intReturningMethod();
            result = 3;

            abc.stringReturningMethod();
            returns("str1", "str2");
            result = new SomeCheckedException();
        }};

        new UnitUnderTest().doSomething();
    }

}