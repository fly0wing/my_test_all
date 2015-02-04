package com.dudo.hotswap.server;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Set;

/**
 * Created by zkai on 2015/2/4.
 */
public class UpgradeNotifier {
    private static Set<ChannelHandlerContext> channelHandlerContexts = new ConcurrentHashSet<>();

    public static void addClient(ChannelHandlerContext e) {
        channelHandlerContexts.add(e);
        System.out.println(channelHandlerContexts.size());
    }
    public static void removeClient(ChannelHandlerContext e) {
        channelHandlerContexts.remove(e);
        System.out.println(channelHandlerContexts.size());
    }

    public static void notifyClient(String msg) {
        ByteBuf msg1 = Unpooled.wrappedBuffer(msg.getBytes(Charsets.UTF_8));
        for (ChannelHandlerContext channelHandlerContext : channelHandlerContexts) {
            try {
                channelHandlerContext.writeAndFlush(msg1.copy());
            } catch (Exception e) {
                channelHandlerContexts.remove(channelHandlerContext);
            }
        }
    }
}

