package com.dudo.hotswap.server;

import com.dudo.hotswap.server.classloader.HotswapDeploy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * Created by zkai on 2014/10/23.
 */
public class NettyServer {
    static final int PORT = Integer.parseInt(System.getProperty("port", "20500"));
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    private static EventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private static EventLoopGroup workerGroup = new NioEventLoopGroup(1);
    private static Channel ch = null;
    private static ServerBootstrap serverBootstrap = null;
    private static HotswapDeploy hotswapDeploy=null;

    public static void main(String[] args) throws CertificateException, InterruptedException, SSLException {
        start();
        synchronized (NettyServer.class) {
            NettyServer.class.wait();
        }
    }


    public static void start() throws CertificateException, SSLException, InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ServerChildHandlerInitializer());

        ch = serverBootstrap.bind(PORT).sync().channel();


        HotswapDeploy hotswapDeploy = new HotswapDeploy("d://classLoader/lib/");
        hotswapDeploy.start();
        logger.info("================" + "Open your web browser and navigate to " +
                "http://127.0.0.1:" + PORT + "/================");
    }

    public static void stop() {
        logger.info("fire netty server stop().the netty server will be stopped");
        try {
            ch.close().awaitUninterruptibly();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        hotswapDeploy.shutdown();
        logger.info("the netty server has stopped");
    }
}
