package com.dudo.guahao;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangkai9
 * @date 2015/5/19
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static HttpClient hc = new DefaultHttpClient();

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", "xxx@gmail.com"));
        params.add(new BasicNameValuePair("pwd", "xxx"));
        params.add(new BasicNameValuePair("save_login", "1"));

        String url = "http://www.oschina.net/action/user/login";

        String body = post(url, params);
        logger.info(body);
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params) {
        return get(url, params, null);
    }

    private static int timeout = 2000;
    public static String get(String url, List<NameValuePair> params, Header[] headers) {
        String body = null;
        HttpGet httpget = null;
        try {
            // Get请求
            httpget = new HttpGet(url);
            httpget.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);//连接时间
            httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);//数据传输时间

            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setHeaders(headers);
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = hc.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
            logger.debug("请求:" + httpget.getURI());
            logger.debug("响应:" + body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * // Post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, List<NameValuePair> params) {
        String body = null;
        try {
            // Post请求
            HttpPost httppost = new HttpPost(url);
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(params));
            // 发送请求
            HttpResponse httpresponse = hc.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
            logger.debug("请求:" + httppost.getURI());
            logger.debug("响应:" + body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    public static String post(String url, List<NameValuePair> params, Header[] asyncHeaders) {
        String body = null;
        try {
            // Post请求
            HttpPost httppost = new HttpPost(url);
            httppost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,timeout);//连接时间
            httppost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,timeout);//数据传输时间
            // 设置参数
            StringBuilder p = new StringBuilder();
//            for (NameValuePair param : params) {
//                p.append(param.getName()).append("=").append(param.getValue());
//                p.append("&");
//            }
//            p.deleteCharAt(p.length() - 1);
//            logger.info(p.toString());
            httppost.setEntity(new UrlEncodedFormEntity(params));
//            httppost.setEntity(new StringEntity(p.toString()));
            httppost.setHeaders(asyncHeaders);
            // 发送请求
            HttpResponse httpresponse = hc.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
            logger.debug("请求:" + httppost.getURI());
            logger.debug("响应:" + body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}