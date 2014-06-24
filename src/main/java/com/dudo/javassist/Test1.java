package com.dudo.javassist;

import javassist.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1. Reading and writing bytecode
 * User: dudu
 * Date: 14-2-14
 * Time: 上午10:53
 */
public class Test1 {


    /**
     * 1. Reading and writing bytecode
     * CtClass 抽象为一个类.通过ClassPool.get()工厂方法得到,
     * 在该方法中,先去内部的Hashtable 中查找,如果存在则返回,
     * 如果不存在,则新建CtClass对象,并返回.
     * <p/>
     * **注:ClassPool 内部存在 Hashtable 类型的变量classes, 保存用于保存CtClass 信息.
     */
    static void t1() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        try {
            cc = pool.get("com.dudo.javassist.test.Rectangle");
            cc.setSuperclass(pool.get("com.dudo.javassist.test.Point"));
            cc.writeFile();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * CtClass 持久化的方法.
     */
    static void t2() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        try {
            cc = pool.get("com.dudo.javassist.test.Rectangle");
            cc.setSuperclass(pool.get("com.dudo.javassist.test.Point"));
            cc.writeFile(); // 保存到文件 当前目录
            cc.writeFile("d://a");//
//            byte[] b = cc.toBytecode(); // 等其他重载方法
//            Class clazz = cc.toClass(); // 等其他重载方法
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Defining a new class
     */
    static void t3() {
        ClassPool pool = ClassPool.getDefault();
        CtClass newClass = null;
        CtClass newInterface = null;

        try {
            //创建一个类
            newClass = pool.makeClass("com.dudo.javassist.test.NewClassByJavassist");
            //创建一个接口
            newInterface = pool.makeInterface("com.dudo.javassist.test.NewInterfaceByJavassist");

            //类实现一个接口
            newClass.addInterface(newInterface);

            newClass.writeFile("d://a");
            newInterface.writeFile("d://a");
        } catch (CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Frozen classes 冻结
     * 当CtClass调用 writeFile(), toClass(), or toBytecode()方法后,改对象将不能被修改
     * 调用cc.defrost();方法解冻后,才可以继续修改.
     */
    static void t4() {
        ClassPool pool = ClassPool.getDefault();
        CtClass newClass = null;
        CtClass newInterface = null;

        try {
            newClass = pool.makeClass("com.dudo.javassist.test.NewClassByJavassist");
            newInterface = pool.makeInterface("com.dudo.javassist.test.NewInterfaceByJavassist");

            newClass.writeFile("d://a");// 调用后,newClass对象被冻结,不能被修改.

//            newClass.defrost();// 调用该方法,解冻对象.
            newClass.addInterface(newInterface); // 在解冻前,尝试修改newClass 将抛出 java.lang.RuntimeException(class is frozen)异常.

            newInterface.writeFile("d://a");
        } catch (CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ClassPool.doPruning
     * ** 在将对象精简之后,对象不能再被defrost解冻.
     * <p/>
     * 如果ClassPool.doPruning被设置成true，那么Javassist会在冻结一个对象的时候对这个对象进行精简.
     * 为了减少ClassPool的内存占用，精简的时候会丢弃class中不需要的属性。
     * 例如Code_attribute结构（即是方法体）会被丢弃。
     * 因此，如果一个CtClass对象被精简了，那么方法的字节码是不能访问的，留下的只有方法名，方法的签名和annotation。
     * 被精简的CtClass对象不能够再被 defrost。
     * <p/>
     * ClassPool.doPruning的默认值是false。
     * 如果要阻止对某一个具体的CtClass对象的精简，需要在这个对象上先调用stopPruing()方法:
     * cc.stopPruning(true);
     */
    static void t5() {
        ClassPool pool = ClassPool.getDefault();
        ClassPool.doPruning = true;
        CtClass newClass = null;

        try {
            newClass = pool.makeClass("com.dudo.javassist.test.NewClassByJavassist");

//            newClass.stopPruning(true);

            newClass.writeFile("d://a");

            newClass.defrost(); // 精简后,再解冻, 则会 报 RuntimeException(was pruned) 异常.

        } catch (CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * debugWriteFile()
     * 此方法主要用在测试中, 此方法将在精简之前把newClass 写到文件,
     * 再解冻,并恢复 是否精简操作的状态值.
     */
    static void t6() {
        ClassPool pool = ClassPool.getDefault();
        CtClass newClass = null;

        newClass = pool.makeClass("com.dudo.javassist.test.NewClassByJavassist");
        newClass.debugWriteFile("d://a");

    }

    /**
     * Class search path
     * 通过insertClassPath() 方法添加class路径.
     */
    static void t7() {
        ClassPool pool = ClassPool.getDefault(); // 只在当前类的JVM class loader中搜索对象.
        try {
            //方法1
            pool.insertClassPath(new ClassClassPath(Test1.class.getClass()));
            //方法2
            pool.insertClassPath("/usr/local/javalib");
            //方法3 这个URL仅仅只是被用来搜索org.javassist.包里的类
            ClassPath cp1 = new URLClassPath("www.javassist.org",80,"/java/","org.javassist.");
            pool.insertClassPath(cp1);

            //方法4 通过字节码直接添加.
            ClassPool cp = ClassPool.getDefault();
            byte[] b = null; // a byte array;
            String name = null; // class name;
            cp.insertClassPath(new ByteArrayClassPath(name, b));
            CtClass cc = cp.get(name);


        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Class search path
     * 从指定的输入流创建的CtClass对象
     *
     * 你可以makeClass()来更快地把一个class文件加入到ClassPool里。
     * 这样做会在搜索路径里包含一个很大的jar文件的时候提高性能。
     * 因为ClassPool是即时读取class文件的，它可能会在每次加载类的时候都需要搜索整个jar文件。
     * makeClass()可以用来优化这个搜索。
     * 这个由makeClass()创建的CtClass会保留在ClassPool里，并且class文件不需要再次读入了。
     *
     */
    static void t8(){
        ClassPool cp = ClassPool.getDefault();
        InputStream ins = null; //an input stream for reading a class file;
        try {
            CtClass cc = cp.makeClass(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        t1();
//        t2();
//        t3();
//        t4();
//        t5();
        t6();
    }

    static void t22() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        try {
            cc.toBytecode();
            cc.debugWriteFile();
        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        }
    }
}
