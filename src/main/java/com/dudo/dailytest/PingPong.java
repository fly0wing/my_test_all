package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 下午2:04
 */
public class PingPong {
    public static      void main(String[] a){
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2000L);
                    pong();
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
//        t.run();
        t.start();
        System.out.print( "Ping" );

    }
    static      void pong(){
        System.out.print( "Pong" );
    }
}
