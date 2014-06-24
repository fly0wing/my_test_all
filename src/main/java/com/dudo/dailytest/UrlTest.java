package com.dudo.dailytest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: zk
 * Date: 13-8-7
 * Time: 下午1:53
 */
public class UrlTest {
    public static void main(String[] args) throws IOException {
        URL url=new URL("");
        URLConnection con = url.openConnection();

    }
}
