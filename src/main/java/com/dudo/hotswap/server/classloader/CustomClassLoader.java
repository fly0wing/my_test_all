package com.dudo.hotswap.server.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
    protected Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class cls = null;
        cls = findLoadedClass(name);
        if(cls == null) {
            try {
                cls = super.loadClass(name, resolve);
            } catch (ClassNotFoundException ignore) {
            }
        }
        if(cls == null)
            cls = getSystemClassLoader().loadClass(name);
        if (cls == null)
            throw new ClassNotFoundException(name);
        if (resolve)
            resolveClass(cls);
        return cls;
    }
}
