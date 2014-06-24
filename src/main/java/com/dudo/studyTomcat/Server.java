package com.dudo.studyTomcat;

import com.dudo.echo.EchoSelectorProtocol;
import com.dudo.echo.TCPProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * User: dudu
 * Date: 14-3-18
 * Time: 下午2:46
 */
public class Server {
    private static final int BUFSIZE = 256;// Buffer size(bytes)
    private static final int TIMEOUT = 3000;// Wait timeout(milliseconds)

    public static void main(String[] args) throws IOException {
//        if (args.length < 1) {// Test for correct#of args
//            throw new IllegalArgumentException("Parameter(s):<Port>...");
//        }
        Selector selector = Selector.open();
//        for (String arg : args) {
//            ServerSocketChannel listnChannel = ServerSocketChannel.open();
//            listnChannel.socket().bind(
//                    new InetSocketAddress(Integer.parseInt(arg)));
//            listnChannel.configureBlocking(false);// must be nonblocking to register
//            listnChannel.register(selector, SelectionKey.OP_ACCEPT);
//        }
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        listnChannel.socket().bind(
                new InetSocketAddress(18080));
        listnChannel.configureBlocking(false);// must be nonblocking to register
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);
        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();// Key is bit mask
                if (key.isAcceptable()) {
                    protocol.handleAccept(key);
                }
                if (key.isReadable()) {
                    protocol.handleRead(key);
                }
                if (key.isValid() && key.isWritable()) {
                    protocol.handleWrite(key);
                }
                keyIter.remove();
            }
        }
    }
}
