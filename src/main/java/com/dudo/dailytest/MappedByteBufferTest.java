package com.dudo.dailytest;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * User: zk
 * Date: 13-8-13
 * Time: 下午3:26
 */
public class MappedByteBufferTest {
    private static String filePath = "D:\\my_test_all\\src\\main\\java\\com\\dudo\\dailytest\\mappedByteBufferTest.txt";

    public static void main(String[] args) {
        randomAccessFileTest();

    }

    private static void randomAccessFileTest() {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

//            raf.setLength(5);

//            raf.write(99);
//            raf.seek(30);
//            raf.writeFloat(3.2f);
//            raf.writeUTF("插入");
            System.out.println(raf.read());
            raf.seek(0);
            byte[] bs = new byte[20];
            System.out.println(raf.read(bs));
            System.out.println(bs[0]);
            System.out.println(bs[1]);
            System.out.println(bs[2]);
            System.out.println("~~~~~~~~~~~~~~~~");
            raf.seek(0);
            System.out.println(raf.readBoolean());
            raf.seek(0);
            System.out.println(raf.readByte());
            raf.seek(0);
            System.out.println(raf.readChar());
            System.out.println("~~~~~~~~~~~~~~~~");
            raf.seek(0);
            System.out.println(raf.readDouble());
            raf.seek(0);
            System.out.println(raf.readFloat());
            raf.seek(0);
            System.out.println(raf.readInt());
            System.out.println("~~~~~~~~~~~~~~~~");
            raf.seek(0);
            System.out.println(raf.readLine());
            raf.seek(0);
            System.out.println(raf.readLong());
            raf.seek(0);
            System.out.println(raf.readShort());
            System.out.println("~~~~~~~~~~~~~~~~");
            raf.seek(0);
            System.out.println(raf.readUnsignedByte());
            raf.seek(0);
            System.out.println(raf.readUnsignedShort());
            raf.seek(0);
            System.out.println(raf.readUTF());
            System.out.println("~~~~~~~~~~~~~~~~");
            raf.seek(0);
            byte [] bs2 = new byte[20];
            raf.readFully(bs2);
            System.out.println(bs2);
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
