package com.dudo.dubbo.provider;

import com.dudo.dubbo.DemoService;

/**
 * Created by zkai on 2014/9/4.
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}