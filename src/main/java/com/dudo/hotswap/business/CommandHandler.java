package com.dudo.hotswap.business;

import com.sun.nio.zipfs.ZipPath;

/**
 * Created by zkai on 2015/2/4.
 */
public class CommandHandler {

    public static String exec(String req) {
        System.out.println("begin exec....");
        ClassLoader classLoader = CommandHandler.class.getClassLoader();
        do {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        } while (classLoader != null);
        try {
            System.out.println(ZipPath.class);
        } catch (Throwable s) {
            s.printStackTrace();
        }

        System.out.println("end exec....");
        return "server:" + req;
    }
}
