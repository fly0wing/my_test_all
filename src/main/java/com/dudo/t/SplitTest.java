package com.dudo.t;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zkai.zhang on 2014/5/23.
 */
public class SplitTest {
    public static void main(String[] args) {
        List<String> nonUpdateSuppliers = Arrays.asList("570".split(",|;"));


        System.out.println(nonUpdateSuppliers.contains(String.valueOf(570)));
    }
}
