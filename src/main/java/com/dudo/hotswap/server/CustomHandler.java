package com.dudo.hotswap.server;

import com.dudo.hotswap.server.classloader.CustomClassLoader;
import com.dudo.hotswap.server.classloader.HotswapDeploy;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class CustomHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CustomHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            final String msgStr = ((ByteBuf) msg).toString(Charsets.UTF_8);
            final CustomClassLoader customClassLoader = HotswapDeploy.getCurrCustomClassLoader();
            final Class<?> commandHandlerClass = customClassLoader.loadClass("com.dudo.hotswap.business.CommandHandler");
            final Method execMethod = commandHandlerClass.getMethod("exec", String.class);
            final String result = (String)execMethod.invoke(commandHandlerClass, msgStr);

            ctx.write(Unpooled.wrappedBuffer(result.getBytes(Charsets.UTF_8)));
            UpgradeNotifier.addClient(ctx);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        try {
            ctx.write(Unpooled.wrappedBuffer("system error".getBytes(Charsets.UTF_8)))
            .addListener(ChannelFutureListener.CLOSE);
        } finally {
            UpgradeNotifier.removeClient(ctx);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        System.out.println("连接断开 。。。");
        UpgradeNotifier.removeClient(ctx);
    }
}
