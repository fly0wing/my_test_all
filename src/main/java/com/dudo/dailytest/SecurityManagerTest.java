package com.dudo.dailytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * User: zk
 * Date: 13-7-30
 * Time: 下午4:31
 */
public class SecurityManagerTest {
    public static void main(String[] args) throws FileNotFoundException {
System.setSecurityManager(new FileSecurityManager());
//        new FileInputStream("c:/444.exe");
        File a  = new File("d:/message.txt");
        a.delete();
        System.out.println(System.getSecurityManager());

    }
}

class FileSecurityManager extends SecurityManager {
    public void checkRead(String file) {
        if (file != null) {
            file = file.toLowerCase();
            if (file.endsWith(".exe") || file.endsWith(".bat") || file.endsWith(".cmd")) {
                throw new SecurityException("无法执行可执行问文件!");
            }
        }
    }

    @Override
    public void checkDelete(String file) {
        if (file != null) {
            file = file.toLowerCase();
            if (file.endsWith(".exe") || file.endsWith(".bat") || file.endsWith(".cmd")) {
                throw new SecurityException("无法执行文件!");
            }
        }
    }
}
