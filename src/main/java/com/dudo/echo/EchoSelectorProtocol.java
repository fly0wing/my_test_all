package com.dudo.echo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * User: zk
 * Date: 13-9-2
 * Time: 上午9:54
 */
public class EchoSelectorProtocol implements TCPProtocol {
    private int bufSize;// Size of I/O buffer

    public EchoSelectorProtocol(int bufSize) {
        this.bufSize = bufSize;
    }

    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        clntChan.configureBlocking(false);// Must be nonblocking to register
// Register the selector with new channel for read and attach byte buffer
        clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer
                .allocate(bufSize));
    }

    public void handleRead(SelectionKey key) throws IOException {
// Clientsocketchannelhaspendingdata
        SocketChannel clntChan = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = clntChan.read(buf);
        System.out.println(decode(buf));

        if (bytesRead == -1) {// Didtheotherendclose?
//            clntChan.close();
        } else if (bytesRead > 0) {
            // Indicateviakeythatreading/writingarebothofinterestnow.
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }
    public static String decode(ByteBuffer buffer) {
        //System.out.println("buffer=" + buffer);
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("utf-8");
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            //System.out.println("charBuffer=" + charBuffer);
            //System.out.println(charBuffer.toString());
            return charBuffer.toString();
        } catch (Exception ex) {
            return "";
        }
    }
    public void handleWrite(SelectionKey key) throws IOException {
/*
		 * Channelisavailableforwriting,andkeyisvalid(i.e.,clientchannel
		 * notclosed).
		 */
        // Retrievedatareadearlier
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();// Preparebufferforwriting
        SocketChannel clntChan = (SocketChannel) key.channel();
        clntChan.write(buf);
        if (!buf.hasRemaining()) {// Buffercompletelywritten?
            // Nothingleft,sonolongerinterestedinwrites
            key.interestOps(SelectionKey.OP_READ);
//            clntChan.close();
        }
        buf.compact();// Makeroomformoredatatobereadin
    }
}
