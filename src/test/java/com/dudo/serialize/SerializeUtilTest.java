package com.dudo.serialize;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangkai9
 * @date 2015/8/5
 */
public class SerializeUtilTest {

    @Test
    public void testSerialize() throws Exception {

    }
    @Test
    public void testName() throws Exception {
        DemoObj creditApply = new DemoObj();
        creditApply.setIdNo("111111111");
        creditApply.setPin("yy-test2");
        byte[] serialize = FstSerializeUtil.serialize(creditApply);
        DemoObj creditApply2 = (DemoObj) FstSerializeUtil.unserialize(serialize);
        assertEquals(creditApply2.getIdNo(),creditApply.getIdNo());
        assertEquals(creditApply2.getPin(),creditApply.getPin());


        String s = new String(serialize);

        System.out.println(Arrays.toString(serialize));
        System.out.println(Arrays.toString(s.getBytes()));
    }

    @Test
    public void testName2() throws Exception {
        DemoObj creditApply = new DemoObj();
        creditApply.setIdNo("111111111");
        creditApply.setPin("yy-test2");
        byte[] serialize = FstSerializeUtil.serialize(creditApply);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : serialize) {
            stringBuilder.append(b).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String x = stringBuilder.toString();
        System.out.println(x);
        String[] split = x.split(",");
        byte[] a = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            a[i] = Byte.parseByte(split[i]);
        }
        DemoObj creditApply2 = (DemoObj) FstSerializeUtil.unserialize(a);
        assertEquals(creditApply2.getIdNo(), creditApply.getIdNo());
        assertEquals(creditApply2.getPin(), creditApply.getPin());
    }

    @Test
    public void testName3() throws Exception {
        DemoObj creditApply = new DemoObj();
        creditApply.setIdNo("111111111");
        creditApply.setPin("yy-test2");
        byte[] serialize = JdkSerializeUtil.serialize(creditApply);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : serialize) {
            stringBuilder.append(b).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String x = stringBuilder.toString();
        System.out.println(x);
        String[] split = x.split(",");
        byte[] a = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            a[i] = Byte.parseByte(split[i]);
        }
        DemoObj creditApply2 = (DemoObj) JdkSerializeUtil.unserialize(a);
        assertEquals(creditApply2.getIdNo(),creditApply.getIdNo());
        assertEquals(creditApply2.getPin(),creditApply.getPin());
    }

    @Test
    public void testName4() throws Exception {
        List creditApply = new ArrayList<Integer>();
        creditApply.add(0);
        byte[] serialize = FstSerializeUtil.serialize(creditApply);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : serialize) {
            stringBuilder.append(b).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String x = stringBuilder.toString();
        System.out.println(x);
        String[] split = x.split(",");
        byte[] a = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            a[i] = Byte.parseByte(split[i]);
        }
        List creditApply2 = (List) FstSerializeUtil.unserialize(a);
        assertEquals(creditApply2.size(), creditApply.size());
        assertEquals(creditApply2.get(0), creditApply.get(0));
    }

    @Test
    public void testName5() throws Exception {
        List creditApply = new ArrayList<Integer>();
        creditApply.add(0);
        byte[] serialize = FstSerializeUtil.serialize(creditApply);

        List creditApply2 = (List) FstSerializeUtil.unserialize(serialize);
        assertEquals(creditApply2.size(), creditApply.size());
        assertEquals(creditApply2.get(0), creditApply.get(0));
    }

    @Test
    public void testName6() throws Exception {
        List creditApply = new ArrayList<Integer>();
        creditApply.add(0);
        byte[] serialize = KryoSerializeUtil.serialize(creditApply);

        List creditApply2 = (List) KryoSerializeUtil.unserialize(serialize);
        assertEquals(creditApply2.size(), creditApply.size());
        assertEquals(creditApply2.get(0), creditApply.get(0));
    }

    @Test
    public void testName7() throws Exception {
        List creditApply = new ArrayList<Integer>();
        creditApply.add(0);
        byte[] serialize = KryoSerializeUtil.serialize(creditApply);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : serialize) {
            stringBuilder.append(b).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String x = stringBuilder.toString();
        System.out.println(x);
        String[] split = x.split(",");
        byte[] a = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            a[i] = Byte.parseByte(split[i]);
        }
        List creditApply2 = (List) KryoSerializeUtil.unserialize(a);
        assertEquals(creditApply2.size(), creditApply.size());
        assertEquals(creditApply2.get(0), creditApply.get(0));
    }
}