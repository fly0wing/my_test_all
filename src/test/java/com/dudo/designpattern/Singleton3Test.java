package com.dudo.designpattern;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

/**
 * User: dudu
 * Date: 13-1-13
 * Time: 下午6:02
 */
@Ignore
public class Singleton3Test {
    @Test
    public void test3() throws IOException, ClassNotFoundException {
        Singleton3 singleton3 = null;
        Singleton3 s = Singleton3.getInstance();
        //先将实例串行化到文件
        FileOutputStream stream = new FileOutputStream("d:/Singleton3.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(s);
        objectOutputStream.flush();
        objectOutputStream.close();
        //从文件读出原有的单例类

        FileInputStream fileInputStream = new FileInputStream("d:/Singleton3.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        singleton3 = (Singleton3) objectInputStream.readObject();
        System.out.println(s);
        System.out.println(singleton3);
        Assert.assertEquals(s, singleton3);
    }


}
