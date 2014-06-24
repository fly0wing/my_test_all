package com.dudo.jvmtest;



import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;

/**
 * 方法区溢出
 * vm args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * TODO 没有产生预期的异常(PermGen space),而是产生 java.lang.ExceptionInInitializerError
 * User: zk
 * Date: 13-8-14
 * Time: 下午3:07
 */
public class JavaMethodAreaOOM {
    static class OOMObject1{}

    public static void main(String[] args) {
        while (true) {

            Enhancer enhancer= new Enhancer();
            enhancer.setSuperclass(OOMObject1.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
