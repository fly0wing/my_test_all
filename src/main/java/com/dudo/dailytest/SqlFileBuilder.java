package com.dudo.dailytest;

import ucar.nc2.util.IO;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author zhangkai9
 * @date 2015/5/6
 */
public class SqlFileBuilder {
    private static int sharding = 1024;

    public static void main(String[] args) throws IOException {
        builderBalance();
        builderBalanceDetail();
    }

    private static void builderBalanceDetail() throws IOException {
        String fileName = "D:\\jd_doc\\total_share_balance_details 1.sql";
        String s = IO.readFile(fileName);
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("_0000");
        for (int i = 0; i < sharding; i++) {
            sb.append(s.replace("total_share_balance_details", "total_share_balance_details" + df.format(i)))
                    .append("\r\n\r\n\r\n");
        }
        IO.writeToFile(sb.toString(), "D:\\jd_doc\\total_share_balance_details2.sql");
    }

    private static void builderBalance() throws IOException {
        String fileName = "D:\\jd_doc\\total_share_balance 1.sql";
        String s = IO.readFile(fileName);
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("_0000");
        for (int i = 0; i < sharding; i++) {
            sb.append(s.replace("total_share_balance", "total_share_balance" + df.format(i)))
                    .append("\r\n\r\n\r\n");
        }
        IO.writeToFile(sb.toString(), "D:\\jd_doc\\total_share_balance2.sql");
    }
}
