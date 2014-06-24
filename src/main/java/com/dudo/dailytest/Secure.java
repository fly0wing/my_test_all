package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-5
 * Time: 下午2:30
 */
public class Secure {
    private String type;
    private String fileKey = type + "File:";
    private String totalKey = type + "s";

    Secure(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        Secure sec = new Secure(null+"test");
        System.out.println(sec.fileKey);
        System.out.println(null+"test");
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}