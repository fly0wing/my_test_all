package com.dudo.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by zkai on 2015/1/29.
 */
public class MyJavaTest implements JavaSamplerClient {
    private SampleResult sampleResult;
    // 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        sampleResult = new SampleResult();
    }

    // 测试执行的循环体，根据线程数和循环次数的不同可执行多次
    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        String name = arg0.getParameter("name"); // 获取在Jmeter中设置的参数值
        String age = arg0.getParameter("age"); // 获取在Jmeter中设置的参数值
        String sex = arg0.getParameter("sex"); // 获取在Jmeter中设置的参数值
        String sleepMs = arg0.getParameter("sleep"); // 获取在Jmeter中设置的参数值
        sampleResult.sampleStart();// jmeter 开始统计响应时间标记

        try {
            // 被测对象调用
            System.out.format("the %s name is %s,%s years,", sex, name, age);
            System.out.println();
            Thread.sleep(1000);
            sampleResult.setSuccessful(true);
        } catch (Throwable e) {
            sampleResult.setSuccessful(false);
            e.printStackTrace();
        } finally {
            sampleResult.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return sampleResult;
    }

    // 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
    }

    // 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("name", "joe");//设置参数，并赋予默认值
        params.addArgument("age", "18");
        params.addArgument("sex", "man");
        params.addArgument("sleep", "1000");
        return params;
    }
}
