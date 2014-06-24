package com.dudo.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * User: zk
 * Date: 13-8-30
 * Time: 下午1:57
 */
public class Client {
    public static void main(String[] args) throws IOException {
        new Client().start();
    }

    public void start() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 8001));
//        sc.connect(new InetSocketAddress("www.baidu.com", 80));
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_CONNECT);
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(32);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
//            System.out.println("keys=" + keys.size());
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("server connected...");
                    break;
                } else if (key.isWritable()) {
                    System.out.println("please input message");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    sc.write(writeBuffer);
                    key.interestOps(SelectionKey.OP_READ);

                } else if (key.isReadable()) {
//                    SocketChannel socketChannel = (SocketChannel) key.channel();

//                    socketChannel.
                    ;
                    int i = sc.read(buffer);
                    buffer.flip();

                    if (i==-1) {
                        System.out.println("o.");
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else {
                        byte[] bs = new byte[i];
                        buffer.get(bs);
                        System.out.println(new String(bs));
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    buffer.clear();
//                    socketChannel.close();
                }
            }
        }
    }
}
