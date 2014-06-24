package com.dudo.dailytest;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zk
 * Date: 13-7-31
 * Time: 上午10:30
 */
public class ListNull<T> {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(null);

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
            System.out.println((String)list.get(i));
        }
    }
    public static<T> void aVoid(T a){

    }
    private void b(T a){}
}
