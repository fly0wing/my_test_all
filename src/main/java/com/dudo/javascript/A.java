package com.dudo.javascript;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.URL;

/**
 * 有js数字精度问题。
 * 0.1+0.2？？
 * 7*.8？？
 */
public class A{
    @Test
    public void testJs1() throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByExtension("js");

        StringReader reader = new StringReader("function getNum(num){return num}");

        engine.eval(reader);

        Invocable invocable = (Invocable)engine;
        Object result = invocable.invokeFunction("getNum", "2");
        System.out.println(result);
    }

    @Test
    public void testJs2() throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByExtension("js");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test.js")));

        engine.eval(reader);

        Invocable invocable = (Invocable)engine;
        Object result = invocable.invokeFunction("getNum", "2");
        System.out.println(result);
    }

    @Test
    public void testOnlineJs() throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByExtension("js");
        URL url2 = new URL("http://10.161.56.231:8888/tjfxpt/resources/js/common/common.js");
        InputStream inputStream = url2.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(reader.readLine());
        engine.eval(reader);

        Invocable invocable = (Invocable)engine;
        Object result = invocable.invokeFunction("fmoney", new Integer[]{10, 2});
        System.out.println(result);

    }
}