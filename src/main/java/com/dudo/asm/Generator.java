//package com.dudo.asm;
//
//import org.objectweb.asm.*;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
///**
// * Created by zkai on 2014/12/21.
// */
//public class Generator{
//    public static void main(String [] args) throws Exception {
//        Account a = new Account();
//        ClassReader cr = new ClassReader("com.dudo.asm.Account");
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
//        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//        byte[] data = cw.toByteArray();
//        File file = new File("d:/Account.class");
//        FileOutputStream fout = new FileOutputStream(file);
//        fout.write(data);
//        fout.close();
//    }
//}
