package com.dudo.guahao;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * @author zhangkai9
 * @date 2015/5/20
 */
public class HeaderUtils {
    /**
     * for list page
     *
     * @return
     */
    public static Header[] getHeaders() {
        Header[] headers = new Header[]{
                new BasicHeader("Cookie", Cons.cookie),
                new BasicHeader("Referer", Cons.referer),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0"),
                new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        };
        return headers;
    }

    public static Header[] getAsyncHeaders(String referer) {
        Header[] headersAsync = new Header[]{
                new BasicHeader("Cookie", Cons.cookie),
                new BasicHeader("Referer", referer),// 关键.前一页的url.
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0"),
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("X-Requested-With", "XMLHttpRequest"),
                new BasicHeader("Accept-Encoding", "gzip, deflate"),
                new BasicHeader("Connection", "keep-alive")
        };
        return headersAsync;
    }

    public static Header[] getSubmitHeaders(String referer) {
        Header[] headersAsync = new Header[]{
                new BasicHeader("Cookie", Cons.cookie),
                new BasicHeader("Referer", referer),// 关键.前一页的url.
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0"),
                new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,* /* ;q=0.8"),
                new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3"),
                new BasicHeader("Accept-Encoding", "gzip, deflate"),
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK")
        };
        return headersAsync;
    }
}
