package com.dudo.jvmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常连池溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 说明"运行时常量池"属于方法区(HotSpot虚拟机中的永久代)的一部分
 * TODO 没有产生预期的效果(java.lang.OutOfMemoryError: PermGen space),而是产生 Java heap space异常.
 * User: zk
 * Date: 13-8-14
 * Time: 下午2:45
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用List 保持这常量池的引用, 避免Full GC回收常量池行为
        List<String> list = new ArrayList<>(1500000);

        //10MB的PermSize在integer范围内足够产生OOM
        int i=0;
        long a = System.currentTimeMillis();
        long b =0;
        while (true) {
            list.add(String.valueOf(i++).intern()+"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
            b= System.currentTimeMillis();
            System.out.println(b-a+":"+ list.size());
        }
    }
}
