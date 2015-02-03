package com.dudo.asm;

import org.objectweb.asm.*;

/**
 * Created by zkai on 2014/12/21.
 */
class DelLoginClassAdapter extends ClassAdapter {
    public DelLoginClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        if (name.equals("login")) {
            return null;
        }
        return cv.visitMethod(access, name, desc, signature, exceptions);
    }
}
