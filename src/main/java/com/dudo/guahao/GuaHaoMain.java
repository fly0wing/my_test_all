package com.dudo.guahao;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhangkai9
 * @date 2015/5/19
 */
public class GuaHaoMain {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(GuaHaoMain.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Cons.isSend = false;//fixme 测试 不发送提交请求.

        String date = "2015-06-02";
        String url = "http://www.bjguahao.gov.cn/comm/ghao.php?hpid=142&keid=1150101&date1=" + date;
//        String url = "http://www.bjguahao.gov.cn/comm/ghao.php?hpid=230&keid=020301&date1=2015-05-29";
//        String url = "http://www.bjguahao.gov.cn/comm/ghao.php?hpid=230&keid=010101&date1=2015-05-26";

        String keys = "半月板,膝";
        VisitDateListPage listPage = new VisitDateListPage(url, keys);
        Future<List<String>> submit = executorService.submit(listPage);

        List<String> details = submit.get();
        if (details.size() > 0) {
            String submitPage = details.get(0);
            logger.info("请求:\n"+listPage.getHrefs().get(submitPage));
            Future<Object> submit1 = executorService.submit(new VisitSubmitPage(submitPage));
            submit1.get();
        } else {
            logger.info("没有号源了....");
        }
        executorService.shutdownNow();

        logger.info("end..");
    }

}
