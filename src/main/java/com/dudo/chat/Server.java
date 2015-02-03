package com.dudo.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: zk
 * Date: 13-8-30
 * Time: 下午1:58
 */
public class Server {
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1);//调整缓存的大小可以看到打印输出的变化
//    private Map<SocketChannel, byte[]> clientMessage = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("localhost", 4700));
        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                }else if (key.isWritable()) {
                    write(key);
                }
                keyIterator.remove();
            }
        }
    }

    private void write(SelectionKey key) {
//        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            ByteBuffer buf = ByteBuffer.wrap("hello~".getBytes());
            buf.flip();// Prepare buffer for writing
            SocketChannel clntChan = (SocketChannel) key.channel();
            clntChan.write(buf);
            if (!buf.hasRemaining()) {// Buffer completely written?
// Nothing left,so no longer interested in writes
                key.interestOps(SelectionKey.OP_READ);
            }
            buf.compact();// Make room for more data to be read in
//
//            socketChannel.write(ByteBuffer.wrap("server:write".getBytes()));
//            System.out.println("server:write");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
//            if (socketChannel!=null) {
//                try {
//                    socketChannel.close();
//                } catch (IOException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//            }
        }

    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();

        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            key.cancel();
            socketChannel.close();
            e.printStackTrace();
            return;
        }

        if (numRead > 0) {
              System.out.print(new String(readBuffer.array()));
        }
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected");
    }
}
