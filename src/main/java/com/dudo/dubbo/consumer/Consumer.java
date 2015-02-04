//package com.dudo.dubbo.consumer;
//
//import com.alibaba.dubbo.rpc.RpcContext;
//import com.billing.internalcontract.BaseResp;
//import com.billing.internalcontract.other.ISeqFacade;
//import com.billing.internalcontract.session.ISessionFacade;
//import com.billing.internalcontract.user.IUserFacade;
//import com.billing.internalcontract.user.IUserTerminalFacade;
//import com.dudo.dubbo.DemoService;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.concurrent.Future;
//
///**
// * Created by zkai on 2014/9/4.
// */
//public class Consumer {
//    public static void main(String[] args) throws Exception {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
//        context.start();
//        long i = System.currentTimeMillis();
//        ISeqFacade demoService = (ISeqFacade) context.getBean("seqFacade");// 获取远程服务代理
//        IUserTerminalFacade userTerminalFacade = (IUserTerminalFacade) context.getBean("userTerminalFacade");
//        IUserFacade userFacade = (IUserFacade) context.getBean("userFacade");
//        ISessionFacade sessionFacade = (ISessionFacade)context.getBean("sessionFacade");
//
//        System.out.println(System.currentTimeMillis() - i + "--1");
//
//        // 异步调用
//        demoService.nextSeq("abc");// 此调用会立即返回null
//        System.out.println(System.currentTimeMillis() - i + "--2");
//        Future<BaseResp> fooFuture = RpcContext.getContext().getFuture();
//
//        userTerminalFacade.activeTerminal(null);
//        Future<BaseResp> respFuture = RpcContext.getContext().getFuture();
//
//        userFacade.login(null);
//        Future<BaseResp> respFuture1 = RpcContext.getContext().getFuture();
//
//        sessionFacade.getBySessionId(null);
//        Future<BaseResp> respFuture2 = RpcContext.getContext().getFuture();
//
//
//        System.out.println(System.currentTimeMillis() - i + "--3");
//        System.out.println("~~~~~~~~~~~~~~~~~" + fooFuture.get());// 显示调用结果
////        System.out.println("~~~~~~~~~~~~~~~~~" + respFuture.get());// 显示调用结果
////        System.out.println("~~~~~~~~~~~~~~~~~" + respFuture1.get());// 显示调用结果
////        System.out.println("~~~~~~~~~~~~~~~~~" + respFuture2.get());// 显示调用结果
//        System.out.println(System.currentTimeMillis() - i + "--4");
//
//
//
//    }
//}
