package com.dudo.guahao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        Cons.cookie = "Hm_lvt_65f844e6a6e140ab52d02690ed38a38b=1437614539,1437701099,1438046412,1438651506; __c_4F6eStgP1vUbq8Lcai94F6eStgP1vUbq8L618G18BS1t6=2832313dfff5ca6f76c87bd96b76aa66; Hm_lpvt_65f844e6a6e140ab52d02690ed38a38b=1438651567";
        // 北医三院, 运动骨科 信息.
        int delay = 7;
        String prefix = "http://www.bjguahao.gov.cn/comm/ghao.php?hpid=142&keid=1150101&date1=";

        // 阜外,心血管
        //http://www.bjguahao.gov.cn/comm/ghao.php?hpid=4&keid=xnk&date1=2015-06-17
//        String prefix = "http://www.bjguahao.gov.cn/comm/ghao.php?hpid=4&keid=xnk&date1=";

        String keys = "半月板,关节镜,膝";
        OffsetDateTime instant = Instant.now().atOffset(ZoneOffset.ofHours(8))
                .plus(delay, ChronoUnit.DAYS);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateTimeFormatter.format(instant);

        String url = prefix + date;
        VisitDateListPage listPage = new VisitDateListPage(url, keys);
        for (int i = 0; i < 30; i++) {// 成功挂号个数.
            run(listPage);
        }
        executorService.shutdownNow();

        logger.info("end..");
    }

    private static void run(VisitDateListPage listPage) throws ExecutionException, InterruptedException {
        Future<List<String>> submit = executorService.submit(listPage);

        List<String> details = submit.get();
        if (details.size() > 0) {
            String submitPage = details.get(0);
            logger.info("请求:\n" + listPage.getHrefs().get(submitPage));
            Future<Object> submit1 = executorService.submit(new VisitSubmitPage(submitPage));
            submit1.get();
            return;
        } else {
            logger.info("没有号源了....");
            Thread.sleep(1000);
            run(listPage);
        }
    }

}
