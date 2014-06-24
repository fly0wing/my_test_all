package com.dudo.studyTomcat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * User: dudu
 * Date: 14-3-19
 * Time: 下午3:28
 */
public class ClientTest {
    static class A extends Thread{
        @Override
        public void run() {
            System.out.println("A start!");
            Socket socket = null;
            String msg = "test";
            DataOutputStream dos = null;
            DataInputStream dis = null;
            try {
                socket = new Socket(InetAddress.getByName("127.0.0.1"),18080);

                socket.setReuseAddress(true);
                socket.setSoTimeout(0);
                socket.setSoLinger(true, 0);
                socket.setTcpNoDelay(true);

                System.out.println("socket:" + socket.toString());
                System.out.println("req:" + msg);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(dis, "utf-8"));
                dos.write(msg.getBytes("utf-8"));
                dos.flush();
                System.out.println("1");
//                sleep(10000);
                System.out.println("2");
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("rsp:" + sb.toString());
//                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            System.out.println("A finish!");

            try {
                dos.write(msg.getBytes("utf-8"));
                dos.flush();
                sleep(20000);
                System.out.println("A 1!");
                dos.write(msg.getBytes("utf-8"));
                dos.flush();
                sleep(20000);
                System.out.println("A 2!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class B extends Thread{
        @Override
        public void run() {
            System.out.println("B start!");
            Socket socket = null;
            String msg = "test2";
            try {
                socket = new Socket(InetAddress.getByName("127.0.0.1"),18080);

                socket.setReuseAddress(true);
                socket.setSoTimeout(100);
                socket.setSoLinger(true, 0);
                socket.setTcpNoDelay(true);


                System.out.println("socket:" + socket.toString());
                System.out.println("req:" + msg);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(dis, "utf-8"));
                dos.write(msg.getBytes("utf-8"));
                dos.flush();
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("rsp:" + sb.toString());
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("B finish!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread aThread = new A();
        Thread bThread = new B();
        aThread.start();
        Thread.sleep(100);
//        bThread.start();
    }
}
