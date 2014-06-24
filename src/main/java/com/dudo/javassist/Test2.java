package com.dudo.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * 2. ClassPool
 * User: dudu
 * Date: 14-2-14
 * Time: 下午4:40
 */
public class Test2 {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
//        t1();
//        t2();
//        t3();
//        t4();
        t5();
    }

    /**
     * Renaming a frozen class for defining a new class
     * 冻结的 CtClass 是不能被SetName 的,
     * 但是可以通过 getAndRename 方法来获取.
     */
    private static void t5() throws IOException, CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("Point");
        cc.writeFile();
        cc.setName("Pair");    // wrong since writeFile() has been called.
        CtClass cc2 = pool.getAndRename("Point", "Pair");

    }

    /**
     * Changing a class name for defining a new class
     *
     * 调用 cc.setName 方法后,类名将会改变, 而且原来的对象将会删除.
     * 一个ClassPool 中 不存在两个相同的 CtClass对象 对应一个class文件.
     * 但是多个ClassPool中,  存在两个相同 对象 对应一个class文件, 可以修改这些对象,生成不同版本.
     *
     * 被冻结的CtClass 不能调用setName
     */
    private static void t4() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        ClassPool pool2 = new ClassPool(true);

        CtClass cc = pool.get("com.dudo.javassist.test.Point");
        CtClass cc1 = pool.get("com.dudo.javassist.test.Point");   // cc1 从缓存中获取.
        cc.setName("Pair"); // 将缓存中的key 从 com.dudo.javassist.test.Point 改变成 Pair
        CtClass cc2 = pool.get("Pair");    // cc2 从缓存中获取
        CtClass cc3 = pool.get("com.dudo.javassist.test.Point");   // cc3 重新创建

        CtClass cc4 = pool2.get("com.dudo.javassist.test.Point");   // cc3 重新创建

        cc3.writeFile("d://a");
        cc2.writeFile("d://a");
        System.out.println("cc == cc1:" + (cc == cc1));// true
        System.out.println("cc == cc2:"+(cc == cc2));// true
        System.out.println("cc == cc3:"+(cc == cc3));// false
        System.out.println("cc2 == cc3:"+(cc2 == cc3));// false

        System.out.println("cc == cc4:"+(cc == cc4));// false


    }

    /**
     * Cascaded ClassPools (级联的 ClassPool)
     *
     * 如果在程序运行在web应用环境中,
     * 创建多个 ClassPool 是必须的,
     * 多个ClassPool 可以像 java.lang.ClassLoader 一样级联.
     *
     *
     */
    private static void t3() {
        ClassPool parent = ClassPool.getDefault();
        ClassPool child = new ClassPool(parent);
        try {
            child.insertClassPath("./classes");
            child.get("aa.bb");// 首先会委托给父 ClassPool 中去查找, 如果不存在,再尝试从子ClassPool中查找.


            child.appendSystemPath();         // the same class path as the default one.
            child.childFirstLookup = true;    // 如果指定 childFirstLookup = true, 则先尝试从子ClassPool中查找,如果没有,再委托给父ClassPool.

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Avoid out of memory 避免内存溢出
     * 方法2:
     * 另一种方法是 不定期的用新 ClassPool 对象 替换旧 ClassPool 对象.
     * 如果ClassPool 被 GC, 那么 ClassPool 里面的 CtClass 同样会被 GC.
     *
     * ClassPool.getDefault() 是单例工厂方法.
     */
    private static void t2() {
        ClassPool cp = new ClassPool(true); // 同ClassPool.getDefault().
    }

    /**
     * Avoid out of memory 避免内存溢出
     *
     * 通过 ClassPool 加载过的CtClass将永久保存在ClassPool中,
     * 为了防止内存溢出,可以将不再需要的对象移除.
     *
     * 方法1:
     * 使用 cc.detach() 将不需要的对象删除.
     * 如果缓存中存在该 全额限定名 的对象, 而且不是本对象的话,
     * 则删除后,将本对象重新加入到cache.
     *
     */
    private static void t1() {
        ClassPool pool = ClassPool.getDefault(); // 只在当前类的JVM class loader中搜索对象.

        CtClass cc = null;
        try {
            cc = pool.get("com.dudo.javassist.test.Rectangle");
            cc.writeFile();
            cc.detach(); //cc 将从pool中移除.
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }



}
