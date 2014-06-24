package com.dudo.javassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 3. Class loader
 * User: dudu
 * Date: 14-2-17
 * Time: 下午4:33
 */
public class Test3 {
    public static void main(String[] args) throws CannotCompileException, InstantiationException, NotFoundException, IllegalAccessException, ClassNotFoundException {
//        t1();
        t2();
    }

    /**
     * Class loading in Java
     * <p/>
     * 在 java 中,可以有多个 类加载器 同时存在,
     * 不同的 类加载器 可以加载相同文件名,但不同 类文件的对象.
     * 这些对象 被认为是不同的.
     * 一个类的实例类型 不能转换为另一个类的类型,
     * 否则会抛出ClassCastException异常.
     * <p/>
     * JVM 不允许动态 重新加载 一个类.
     * 然而,JPDA (Java Platform Debugger Architecture) 为重新加载一个类提供了有限的能力
     */
    private static void t2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myLoader = new MyClassLoader(ClassLoader.getSystemClassLoader(),"D:\\my_test_all\\target\\classes");
        Class clazz = myLoader.loadClass("com.dudo.javassist.Hello");
        Object obj = clazz.newInstance();
        Hello b = (Hello) obj;    // this always throws ClassCastException.

    }

    private static void t1() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Hello h1 = new Hello(); // 如果Hello 已经被加载到JVM, 则不能使用如下方法.即 放开注释,则会报错(LinkageError).
        h1.say();

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.dudo.javassist.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello) c.newInstance();
        h.say();
    }
}

class Hello {
    public void say() {
        System.out.println("Hello");
    }
}

class MyClassLoader extends ClassLoader {
//    private static final Logger LOG = LoggerFactory.getLogger(MyClassLoader.class);
    private String baseDir;

    public MyClassLoader(ClassLoader parent, String baseDir) {
        super(parent);
        this.baseDir = baseDir;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassBytes(name);
        Class theClass = defineClass(name, bytes, 0, bytes.length);
        //A if (theClass == null) throw new ClassFormatError();
        return theClass;
    }

    private byte[] loadClassBytes(String className) throws ClassNotFoundException {
        try {
            String classFile = getClassFile(className);
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException fnfe) {
            throw new ClassNotFoundException(className);
        }
    }

    private String getClassFile(String name) {
        StringBuffer sb = new StringBuffer(baseDir);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }
}

