package com.dudo.classload.a;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by zkai on 2015/2/4.
 */
class CustomClassLoader extends ClassLoader {
    private String basedir; // 需要该类加载器直接加载的类文件的基目录
    private HashSet dynaclazns; // 需要由该类加载器直接加载的类名

    public CustomClassLoader(String basedir, String[] clazns) {
        super(null); // 指定父类加载器为 null
        this.basedir = basedir;
        dynaclazns = new HashSet();
        loadClassByMe(clazns);
    }

    private void loadClassByMe(String[] clazns) {
        for (int i = 0; i < clazns.length; i++) {
            loadDirectly(clazns[i]);
            dynaclazns.add(clazns[i]);
        }
    }

    private Class loadDirectly(String name) {
        Class cls = null;
        StringBuffer sb = new StringBuffer(basedir);
        String classname = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + classname);
        File classF = new File(sb.toString());
        try {
            cls = instantiateClass(name, new FileInputStream(classF),
                    classF.length());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cls;
    }

    private Class instantiateClass(String name, InputStream fin, long len) {
        byte[] raw = new byte[(int) len];
        try {
            fin.read(raw);
            fin.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return defineClass(name, raw, 0, raw.length);
    }

    protected Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class cls = null;
        cls = findLoadedClass(name);
        if (!this.dynaclazns.contains(name) && cls == null)
            cls = getSystemClassLoader().loadClass(name);
        if (cls == null)
            throw new ClassNotFoundException(name);
        if (resolve)
            resolveClass(cls);
        return cls;
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
                String url = "d://classLoader/lib";// "/lib/yerasel/GetPI.jar";
                if (cl == null) {
                    cl = new CustomClassLoader(url,
                            new String[]{"com.wyfbilling.utils.date.DateConvertUtils"});
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
                a = format;
                c = cls;
//                Method m = foo.getClass().getMethod("Output", new Class[]{});
//                m.invoke(foo, new Object[]{});
                Thread.sleep(500);
                cl = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

