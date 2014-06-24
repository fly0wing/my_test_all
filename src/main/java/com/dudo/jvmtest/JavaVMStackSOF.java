package com.dudo.jvmtest;

/**
 * 虚拟机栈和本地方法栈溢出
 * 栈容量(-Xss128k)
 * java.lang.StackOverflowError
 * User: zk
 * Date: 13-8-14
 * Time: 下午2:14
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        this.stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try{
            sof.stackLeak();
        }catch(Throwable throwable){
            System.out.println("stack length:"+sof.stackLength);//stack length:2246
            throw throwable;
        }
    }
}
