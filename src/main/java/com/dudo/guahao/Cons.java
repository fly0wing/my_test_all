package com.dudo.guahao;

import java.util.Random;

/**
 * @author zhangkai9
 * @date 2015/5/21
 */
public class Cons {
    public static boolean isSend = true;
    public static String cookie = "Hm_lvt_65f844e6a6e140ab52d02690ed38a38b=1432603123,1433208208,1433294346,1433812985; __c_4482r0aB5AOkcai94482r0aB5AOk2f6ULJM0e6p=0ec399bc613a98bcac679430d4e9126f; Hm_lpvt_65f844e6a6e140ab52d02690ed38a38b=1433813027";
    public static String referer = "http://www.bjguahao.gov.cn/comm/content.php?hpid=142&keid=1150101";

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Random random = new Random();
        //1954
        for (int i = 0; i < 1000000000; i++) {
            random.setSeed(System.currentTimeMillis());
            int i1 = random.nextInt(1000000000);//17514
//            long i2 = System.currentTimeMillis();//16086
//            System.out.println(i1);
//            System.currentTimeMillis();
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
