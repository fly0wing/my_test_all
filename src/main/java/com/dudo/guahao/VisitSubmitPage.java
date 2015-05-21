package com.dudo.guahao;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * @author zhangkai9
 * @date 2015/5/20
 */
public class VisitSubmitPage implements Callable<Object> {
    private String url;
    private static Logger logger = LoggerFactory.getLogger(VisitSubmitPage.class);

    public VisitSubmitPage(String url) {
        this.url = url;
    }

    @Override
    public Object call() throws Exception {
        String start = "$.get(\"../shortmsg/dx_code.php";
        String baseUrl = "http://www.bjguahao.gov.cn/comm/";
        String end = "\n";
        String b = HttpClientUtils.get(url, new ArrayList<>(), HeaderUtils.getHeaders());
        int i = b.indexOf(start);
        String yzm = null;
        if (i > 0) {
            String temp = b.substring(i);
            yzm = temp.substring(0, temp.indexOf(end));
        }
        if (StringUtils.isBlank(yzm)) {
            logger.info("解析验证码url 错误." + yzm);
            return null;
        }
        String hpid = null;
        String ksid = null;
        String datid = null;
        Map<String, String> hiddenKV;
        String tpost;
        try {
            hpid = XMLUtils.byPathGetFirstNodeAttr(b, "//input[@id='code_hpid']", "value");
            ksid = XMLUtils.byPathGetFirstNodeAttr(b, "//input[@id='code_ksid']", "value");
            datid = XMLUtils.byPathGetFirstNodeAttr(b, "//input[@id='code_datid']", "value").replace("?", "");
            tpost = XMLUtils.byPathGetFirstNodeAttr(b, "//input[@name='tpost']", "value");
            hiddenKV = XMLUtils.byPathGetNameVal(b, "//input[@type='hidden']");
        } catch (XPatherException e) {
            e.printStackTrace();
            logger.info("解析验证码url参数 错误." + e.getMessage());
            return null;
        }
        //打印确认信息
        try {
            HtmlCleaner hc = new HtmlCleaner();
            TagNode tn = hc.clean(b);
            String confirmDesc = ((TagNode) tn.evaluateXPath("//p[@class='guahaol']")[0]).getText().toString();
            logger.info("请确认信息:" + confirmDesc);
        } catch (Exception e) {
        }

        // 发送验证码
//        logger.info(yzm);
        yzm = yzm.replace("\"+hpid+\"", hpid)
                .replace("\"+baoxiao,null,callback);", "0")
                .replace("\"+ybkh+\"", "")
                .replace("\"+jiuz+\"", "")
                .replace("\"+ksid+\"", ksid)
                .replace("\"+datid+\"", datid)
                .replace("\"+\"", "")
                .replace("$.get(\".", "").replace("?&", "&");

        if (Cons.isSend) {
            HttpClientUtils.get(baseUrl + yzm, new ArrayList<>(), HeaderUtils.getAsyncHeaders(url));
        }

        // 短语验证码 输入:
        Scanner scanner = new Scanner(System.in);
        logger.info("请输入验证码:");
        String msgYzm;
        while (true) {
            msgYzm = scanner.nextLine();
            if (!StringUtils.isNoneBlank(msgYzm) || msgYzm.length() != 4) {
                logger.error("输入有误,请重新输入:");
            } else {
                break;
            }
        }

        String submitUrl = "http://www.bjguahao.gov.cn/comm/TG/ghdown.php";
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("dxcode", msgYzm));//验证码
        params.add(new BasicNameValuePair("jiuz", ""));
        params.add(new BasicNameValuePair("ybkh", ""));
        params.add(new BasicNameValuePair("baoxiao", "0"));
        for (Map.Entry<String, String> e : hiddenKV.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().replace("?", "")));
        }

        if (Cons.isSend) {
            String post = HttpClientUtils.post(submitUrl, params, HeaderUtils.getSubmitHeaders(url));
            logger.info("结果:" + post);
        }
        return null;
    }
}
