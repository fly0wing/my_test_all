package com.dudo.dailytest;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

import java.nio.ByteOrder;

/**
 * Created by zkai on 2014/10/20.
 */
public class BufferTest {

    @Test
    public void testEndianness() {
        ChannelBuffer buffer;

        int value = 1255555;
        int value2 = 3;
//ByteOrder.BIG_ENDIAN
        int capacity = 8;
        buffer = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, capacity);
        buffer.writeInt(value);
        buffer.writeInt(value2);

        for (int i = 0; i < capacity; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }
        System.out.println("============================");

//ByteOrder.LITTLE_ENDIAN
        buffer = ChannelBuffers.buffer(ByteOrder.LITTLE_ENDIAN, capacity);
        buffer.writeInt(value);
        buffer.writeInt(value2);

        for (int i = 0; i < capacity; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }
        System.out.println("============================");
//ByteOrder.nativeOrder()
        buffer = ChannelBuffers.buffer(ByteOrder.nativeOrder(), capacity);
        buffer.writeInt(value);
        buffer.writeInt(value2);

        for (int i = 0; i < capacity; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }
    }
}
