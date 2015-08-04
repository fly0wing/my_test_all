package com.dudo.designpattern;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

/**
 * User: dudu
 * Date: 14-1-13
 * Time: 下午6:02
 */
@Ignore
public class Singleton4Test {
    @Test
    public void test4() throws IOException, ClassNotFoundException {
        Singleton4 singleton4 = null;
        Singleton4 s = Singleton4.getInstance();
        //先将实例串行化到文件
//        FileOutputStream stream = new FileOutputStream("d:/Singleton4.txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
//        objectOutputStream.writeObject(s);
//        objectOutputStream.flush();
//        objectOutputStream.close();
        //从文件读出原有的单例类

        FileInputStream fileInputStream = new FileInputStream("d:/Singleton4.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        singleton4 = (Singleton4) objectInputStream.readObject();
        System.out.println(s);
        System.out.println(singleton4);
        Assert.assertEquals(s, singleton4);
    }

    @Test
    public void test4_2() throws IOException, ClassNotFoundException {
        Singleton4 singleton4 = null;
        Singleton4 s = Singleton4.getInstance();
        //先将实例串行化到文件
//        FileOutputStream stream = new FileOutputStream("d:/Singleton4.txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
//        objectOutputStream.writeObject(s);
//        objectOutputStream.flush();
//        objectOutputStream.close();
        //从文件读出原有的单例类

        FileInputStream fileInputStream = new FileInputStream("d:/Singleton4.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        singleton4 = (Singleton4) objectInputStream.readObject();

        FileInputStream fileInputStream2 = new FileInputStream("d:/Singleton4.txt");
        ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
        s = (Singleton4) objectInputStream2.readObject();

        System.out.println(s);
        System.out.println(singleton4);
        Assert.assertEquals(s, singleton4);
    }


}
