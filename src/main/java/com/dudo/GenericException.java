package com.dudo;

/**
 * User: dudu
 * Date: 14-2-11
 * Time: 下午4:28
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型在异常中的限制
 * @author JoyoungZhang@gmail.com
 *
 */
public class GenericException<T extends Throwable> {
    public static void main(String[] args) throws Throwable {
        erasureTest(new IllegalAccessException(),null,"");

    }
    void test(T t) throws T {//T被擦除到了Throwable, 可以声明形式类型参数这个异常
        try {
            throw t;    //T被擦除到了Throwable，这里也可以编译通过
//        }catch(T ex){   //compile error 1.catch块不能捕获泛型类型的异常
            //因为在编译器和运行期间都必须知道异常的确切类型，即使知道T被擦除到了Throwable也没用
        } catch (Throwable throwable) {

        }
    }

   static  <V extends  Object>V erasureTest(Throwable t,List<? extends Number> a,V v) throws Throwable {
        try{
            throw t;
        }catch(Throwable ex){
        }
        return v;
    }
    <V>void test2(V t) throws T {//T被擦除到了Throwable, 可以声明形式类型参数这个异常

    }

    void  t3() {
//        List<Number> a = new ArrayList<Integer>();
//        List<Number>[] a = new Integer[10];

    }
}

//compile error
//2.泛型类不能直接或间接继承Throwable
//并不是说泛型形式类型参数不能继承Throwable
//class MyException4 <T extends Throwable> extends Throwable{
//}
//class MyException <T extends ThreadLocal> extends Throwable{
//}
//class MyException2 <T extends ThreadLocal> extends ThreadLocal{
//}
//class MyException3 <T extends Throwable> extends ThreadLocal{
//}


