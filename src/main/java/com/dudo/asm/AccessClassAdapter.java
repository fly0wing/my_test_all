package com.dudo.asm;

import org.objectweb.asm.*;

/**
 * Created by zkai on 2014/12/21.
 */
class AccessClassAdapter extends ClassAdapter {
    public AccessClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    public FieldVisitor visitField(final int access, final String name,
                                   final String desc, final String signature, final Object value) {
        int privateAccess = Opcodes.ACC_PRIVATE;
        return cv.visitField(privateAccess, name, desc, signature, value);
    }
}
