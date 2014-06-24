package com.dudo.jvmtest;

/**
 * 虚拟机栈和本地方法栈溢出
 * 栈容量(-Xss2M)
 *
 * java.lang.OutOfMemoryError: unable to create new native thread
 * User: zk
 * Date: 13-8-14
 * Time: 下午2:27
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }
    public void stackLeakByThread() {
        while (true) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom =new JavaVMStackOOM();
        // oom.stackLeakByThread(); 会使系统产生假死,测试请慎重.
    }
}
