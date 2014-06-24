package com.dudo.echo;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * User: zk
 * Date: 13-9-2
 * Time: 上午9:55
 */
public class EchoClient {
    public static void main(String args[]) throws Exception {
        if ((args.length < 2) || (args.length > 3))// Testforcorrect#ofargs
            throw new IllegalArgumentException(
                    "Parameter(s): <Server> <Word> [<Port>]");
        String server = args[0];// Server nameor IP address
// Convert input String to bytes using the default charset
        byte[] argument = args[1].getBytes();
        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
// Create channel and set to non blocking
        SocketChannel clntChan = SocketChannel.open();
        clntChan.configureBlocking(false);
// Initiate connection to server and repeatedly poll until complete
        clntChan.connect(new InetSocketAddress(server, servPort));
        Selector selector = Selector.open();
        clntChan.register(selector, SelectionKey.OP_CONNECT);

        ByteBuffer writeBuf = ByteBuffer.wrap(argument);
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
        int totalBytesRcvd = 0;// Total bytes received so far
        int bytesRcvd;// Bytes received in last read
        Scanner scanner = new Scanner(System.in);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keysIterator = keys.iterator();
            while (keysIterator.hasNext()) {
                SelectionKey key = keysIterator.next();
                SocketChannel channel = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    clntChan.finishConnect();
                    System.out.print(".");// Do some thing else
                    key.interestOps(SelectionKey.OP_WRITE);
                }else if (key.isWritable()) {
                    System.out.println("please input message");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    channel.write(writeBuffer);
                    key.interestOps(SelectionKey.OP_READ);
                }else if (key.isReadable()) {
                    int a =channel.read(readBuf);
                    byte[] bs = new byte[a];
                    if (a == -1) {
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    readBuf.flip();
                    readBuf.get(bs);
                    System.out.println(new String(readBuf.array()));
                    System.out.println(new String(bs));
                    readBuf.clear();
                }
                keysIterator.remove();
            }
        }



//        while (totalBytesRcvd < argument.length) {
//            if (writeBuf.hasRemaining()) {
//                clntChan.write(writeBuf);
//            }
//            if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
//                throw new SocketException("Connection closed prematurely");
//            }
//            totalBytesRcvd += bytesRcvd;
//            System.out.print(".");// Do something else
//        }
//        System.out.println("Received:" + // convert to String per default charset
//                new String(readBuf.array(), 0, totalBytesRcvd));
//        clntChan.close();
    }
}
