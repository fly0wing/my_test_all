package com.dudo;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author zhangkai9
 * @date 2015/6/12
 */
@Ignore
public class TestUmp {
    @Test
    public void testName() throws Exception {
        String [] [] strings = new String[][]{
                new String [] {"cfuser.sendSMS","发送短信服务"},
                new String [] {"cfuser.CreditUserService.queryBaseUserList","查询cf_baseuser"},
                new String [] {"cfuser.CreditUserService.updateBaseUserbyIdno","更新用户基本表(queryBaseUser)"},
                new String [] {"cfuser.CreditUserService.queryIsCFUser","查询cf_baseuser,用于页面查询是否开旅游白条接口"},
                new String [] {"cfuser.RiskUserTagService.TagDataInfo",""},
                new String [] {"cfuser.RiskUserTagService.findUserTag",""},
                new String [] {"cfuser.RiskUserTagService.updateUserTag",""},
                new String [] {"cfuser.JRGServiceRpc.getUserInfoByIdNo","通过身份证号查询会员相关信息,是否是白条用户"},
                new String [] {"cfuser.GongAnService.gongAnServiceVerify",""},
                new String [] {"cfuser.ErpService.getInvestor","取得商户号对应的出资方号"},
                new String [] {"cfuser.CfBusinessService.selectOrder","查询订单详情"},
                new String [] {"cfuser.CfBusinessService.getNoRefundPlans7DaysByPIN","京东pin查询近7天还款"},
                new String [] {"cfuser.CDSCreditServiceRpc.CDSApply",""},
                new String [] {"cfuser.redis.delete",""},
                new String [] {"cfuser.redis.write",""},
                new String [] {"cfuser.redis.read",""},
                new String [] {"cfuser.BtUserService.personAccountQuery",""},
                new String [] {"cfuser.BtUserService.queryInfoById",""},
                new String [] {"cfuser.BtUserService.queryActiveInfo",""},
                new String [] {"cfuser.BtUserService.queryInfo",""},
                new String [] {"cfuser.AccountServiceRpc.SelectAccountList","查询所有账户情况"},
                new String [] {"cfuser.CreditUserService.queryLoan","查询账户的贷款总额"},
                new String [] {"cfuser.BtUserService.actTransfer","白条转账接口, 更新可用额度"},
                new String [] {"cfuser.CreditUserService.queryUserLoan","查询总贷款数和近七日的欠款"},
                new String [] {"cfuser.ApplyLoanService.queryUser","查询用户的授信信息"},
                new String [] {"cfuser.ApplyLoanService.updateUserBalance","更新用户额度"},
                new String [] {"cfuser.ApplyLoanService.checkRegister","开户接口"},
                new String [] {"cfuser.CreditUserService.queryCreditUserAccount","查询用户信用表和账户表"},
                new String [] {"cfuser.CreditUserService.queryBalanceUserDetails","查询额度明细表"},
                new String [] {"cfuser.CreditUserService.queryBalanceUser","查询用户额度表"},
                new String [] {"cfuser.CreditUserService.queryCreditUser","查询用户信用表"},
                new String [] {"cfuser.CreditAccountService.countAccounts","查询个人账户总数"},
                new String [] {"cfuser.CreditAccountService.queryFineAccounts","取得罚金账户"},
                new String [] {"cfuser.CreditAccountService.queryAccounts","查询个人账户"},
                new String [] {"cfuser.CreditAccountService.createCreditAccount","创建个人账户"},
                new String [] {"cfuser.CreditUserService.updateBaseUser","更新基本用户表baseuser"},
                new String [] {"cfuser.CreditUserService.queryBaseUser","查询基本用户表baseuser"},
                new String [] {"cfuser.IdGeneratorService.getSerialNo","生成流水号"},
                new String [] {"cfuser.JRGServiceRpc.getRealNameFacade","取得实名信息。"},
                new String [] {"cfuser.JRGServiceRpc.getAuthFacade","查询实名认证信息"},
                new String [] {"cfuser.JRGServiceRpc.getLoginIdentify","通过身份证查询用户信息"},
                new String [] {"cfuser.JRGServiceRpc.getLoginIdentifyMap","通过会员id查询相关信息"},
                new String [] {"cfuser.JRGServiceRpc.findRealNamedCustomer","通过身份证查询id"},
                new String [] {"cfuser.CDSCreditServiceRpc.CreatePreCreditInfo","CDS开户"},
                new String [] {"cfuser.AccountServiceRpc.personAccountQuery","查询个人账户信息"},
                new String [] {"cfuser.AccountServiceRpc.personAccountCreate","创建个人账户"},
        };

        String temp = "alarmLevelThresholdValues%5B0%5D.atvThresholdName=med_tp50&alarmLevelThresholdValues%5B0%5D.atvThresholdValue=500&alarmLevelThresholdValues%5B0%5D.atvModuleCode=2&alarmLevelThresholdValues%5B0%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B0%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B1%5D.atvThresholdName=med_tp90&alarmLevelThresholdValues%5B1%5D.atvThresholdValue=500&alarmLevelThresholdValues%5B1%5D.atvModuleCode=2&alarmLevelThresholdValues%5B1%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B1%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B2%5D.atvThresholdName=med_tp99&alarmLevelThresholdValues%5B2%5D.atvThresholdValue=2000&alarmLevelThresholdValues%5B2%5D.atvModuleCode=2&alarmLevelThresholdValues%5B2%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B2%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B3%5D.atvThresholdName=med_tp_max&alarmLevelThresholdValues%5B3%5D.atvThresholdValue=3000&alarmLevelThresholdValues%5B3%5D.atvModuleCode=2&alarmLevelThresholdValues%5B3%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B3%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B4%5D.atvThresholdName=med_tp999&alarmLevelThresholdValues%5B4%5D.atvThresholdValue=2500&alarmLevelThresholdValues%5B4%5D.atvModuleCode=2&alarmLevelThresholdValues%5B4%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B4%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B5%5D.atvThresholdName=med_tp_avg&alarmLevelThresholdValues%5B5%5D.atvThresholdValue=2000&alarmLevelThresholdValues%5B5%5D.atvModuleCode=2&alarmLevelThresholdValues%5B5%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B5%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B6%5D.atvThresholdName=med_tp_continue_count&alarmLevelThresholdValues%5B6%5D.atvThresholdValue=3&alarmLevelThresholdValues%5B6%5D.atvModuleCode=2&alarmLevelThresholdValues%5B6%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B6%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B7%5D.atvThresholdName=med_tp_alarm_timeinterval&alarmLevelThresholdValues%5B7%5D.atvThresholdValue=5&alarmLevelThresholdValues%5B7%5D.atvModuleCode=2&alarmLevelThresholdValues%5B7%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B7%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B8%5D.atvThresholdName=med_avblerate_continue_count&alarmLevelThresholdValues%5B8%5D.atvThresholdValue=3&alarmLevelThresholdValues%5B8%5D.atvModuleCode=2&alarmLevelThresholdValues%5B8%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B8%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B9%5D.atvThresholdName=med_avblerate_value&alarmLevelThresholdValues%5B9%5D.atvThresholdValue=99&alarmLevelThresholdValues%5B9%5D.atvModuleCode=2&alarmLevelThresholdValues%5B9%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B9%5D.atvAlarmlevelWeights=1&alarmLevelThresholdValues%5B10%5D.atvThresholdName=med_avblerate_alarm_timeinterval&alarmLevelThresholdValues%5B10%5D.atvThresholdValue=5&alarmLevelThresholdValues%5B10%5D.atvModuleCode=2&alarmLevelThresholdValues%5B10%5D.atvUmpKey=replace#1#&alarmLevelThresholdValues%5B10%5D.atvAlarmlevelWeights=1&tpMethod.accessKey=replace#1#&tpMethod.analysisFrequency=5&tpMethod.appId=6537&tpMethod.appName=cfuser.jr.jd.local&tpMethod.departCode=12780&ignoreWorkerStep=true&alarmConfigs%5B0%5D.accessKey=replace#1#&alarmConfigs%5B0%5D.moduleCode=2&alarmConfigs%5B0%5D.alarmWeight=1&alarmConfigs%5B0%5D.alarmWay=1%2C2%2C4&alarmConfigs%5B0%5D.sign=1&key.accessKey=replace#1#&key.appId=6537&tpMethod.methodId=99244&key.keyRemark=replace#2#";

        for (String[] string : strings) {
            System.out.println(temp.replace("replace#1#",string[0]).replace("replace#2#",string[1]));
        }

    }


}
