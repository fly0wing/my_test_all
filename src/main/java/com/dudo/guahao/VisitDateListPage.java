package com.dudo.guahao;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * @author zhangkai9
 * @date 2015/5/20
 */
public class VisitDateListPage implements Callable<List<String>> {
    private String url;
    String keys;
    private Map<String, String> hrefs = new LinkedHashMap<>();
    private Map<String, String> priorityHrefs = new LinkedHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(VisitDateListPage.class);

    public VisitDateListPage(String url) {
        this(url, null);
    }

    public VisitDateListPage(String url, String keys) {
        this.url = url;
        this.keys = keys;
    }

    public Map<String, String> getHrefs() {
        return hrefs;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public List<String> call() throws Exception {
        String body = HttpClientUtils.get(url, new ArrayList<>(), HeaderUtils.getHeaders());
        String baseUrl = "http://www.bjguahao.gov.cn/comm/";
        try {
            Object[] objects = XMLUtils.byPath(body, "//a");
            for (Object object : objects) {
                if (object instanceof TagNode) {
                    String href = ((TagNode) object).getAttributeByName("href");
                    String toUrl = (baseUrl + href).replace("/./", "/");
                    TagNode td = ((TagNode) object).getParent().getParent();
                    Object[] tds = td.evaluateXPath("//td");
                    String desc = null;

                    String yisheng = null; //医生,姓名
                    String zhicheng = null; // 职称
                    String fee = null; //挂号费

                    try {
                        yisheng = ((TagNode) tds[4]).getText().toString(); //医生,姓名
                        zhicheng = ((TagNode) tds[5]).getText().toString(); // 职称
                        fee = ((TagNode) tds[6]).getText().toString(); //挂号费
                    } catch (Exception e) {
                    }
                    try {
                        TagNode zhuanchang = (TagNode) tds[7];// 专长
                        String text = zhuanchang.getText().toString();
                        desc = String.format("医生:%s,职称:%s,挂号费:%s,专长:%s,href:%s",
                                yisheng, zhicheng, fee, zhuanchang.getText(), toUrl);
                        if (StringUtils.isNotBlank(keys)) {
                            for (String key : keys.split(",")) {
                                if (text.contains(key)) {
                                    priorityHrefs.put(toUrl, desc);
                                    hrefs.put(toUrl, desc);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                    hrefs.put(toUrl, desc);
                }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        logger.info("可挂号是:\n" + Joiner.on("\n").join(hrefs.values()));
        ArrayList<String> list;
        if (priorityHrefs.size() > 0) {
            list = new ArrayList<>(priorityHrefs.keySet());
        } else {
            list = new ArrayList<>(hrefs.keySet());
            Collections.reverse(list);
        }
        return list;
    }
}
