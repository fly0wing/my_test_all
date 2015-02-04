package com.dudo.classload.b;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

/**
 * Created by zkai on 2015/2/4.
 */
public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader(String libDir) throws MalformedURLException {
        super(new URL[]{}, null);

        File dir = new File(libDir);

        if (dir.isFile())
            addURL(dir.toURI().toURL());
        else if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    addURL(file.toURI().toURL());
                }
            }
        }
    }
}

/*
* 每隔500ms运行一次，不断加载class
*/
class Multirun implements Runnable {
    public void run() {
        CustomClassLoader cl = null;
        try {
            Method a = null;
            Class c = null;
            while (true) {
                // 每次都创建出一个新的类加载器
                // class需要放在自己package名字的文件夹下
//                String url = System.getProperty("user.dir") + "/lib";// "/lib/yerasel/GetPI.jar";
                String url = "d://classLoader/lib/";// "/lib/yerasel/GetPI.jar";
                if (cl == null) {
                    cl = new CustomClassLoader(url);
                }
                Class cls = cl.loadClass("com.wyfbilling.utils.date.DateConvertUtils");
                Object foo = cls.newInstance();
                // 被调用函数的参数
                Method format = foo.getClass().getMethod("format", new Class[]{Date.class, String.class});
                System.out.println(format.invoke(cls, new Date(), "yyyy-MM-dd"));
                Modifier.isStatic(format.getModifiers());
                System.out.println(a);
                System.out.println(format);
                System.out.println(a == format);

                System.out.println(c);
                System.out.println(cls);
                System.out.println(c == cls);
                System.out.println(cls.getClassLoader());
                a = format;
                c = cls;
//                Method m = foo.getClass().getMethod("Output", new Class[]{});
//                m.invoke(foo, new Object[]{});
                Thread.sleep(500);
//                cl = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


