package com.dudo.javassist.test;

import javassist.*;

import java.io.IOException;

/**
 * User: dudu
 * Date: 14-2-14
 * Time: 下午1:16
 */
public class Test2 {
    public static void main(String[] args) {
        t1();
    }

    private static void t1() {
        ClassPool pool = ClassPool.getDefault();
        //设置目标类的路径
        try {
//            pool.insertClassPath("d:/a");

            //获得要修改的类
            CtClass cclass = pool.get("com.dudo.javassist.test.SistTest");
            //设置方法需要的参数
            CtClass[] param = new CtClass[1];
            param[0]=pool.get("java.lang.String");
            //得到方法
            CtMethod method = cclass.getDeclaredMethod("getMessage", param);
            //插入新的代码
//            method.insertAfter("{str += \"no ,\" + $1;}");//$1表示第一个参数
            method.insertAt(1,"{System.out.println(1);}");
            method.insertAt(2,"{System.out.println(2);}");
            method.insertAt(13,"System.out.println(3);");
//            method.setBody("{return \"no,\"+$1;}");
            //保存到文件里
            cclass.writeFile("d:/a");//这里把.class文件再写回它原来所在地目录，如果没有这个参数，则会在当前项目的根目录生成新的.class文件

            //调用修改后的类
            Class c = cclass.toClass();
            SistTest s = (SistTest) c.newInstance();
            System.out.println(s.getMessage("a"));
        } catch (NotFoundException | IllegalAccessException | InstantiationException
                | IOException | CannotCompileException e) {
            e.printStackTrace();  
        }
    }
}
