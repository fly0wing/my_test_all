package com.dudo.redis;

import com.wyfbilling.cache.CacheUtils;
import net.oschina.j2cache.CacheObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CacheUtilsTest {
    public static void main(String[] args) {

        System.setProperty("java.net.preferIPv4Stack", "true"); //Disable IPv6 in JVM
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                System.out.print("> ");
                System.out.flush();

                String readLine = in.readLine();
                if (readLine == null) {
                    Thread.sleep(100);
                    continue;
                }
                String line = readLine.trim();
                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit"))
                    break;

                String[] cmds = line.split(" ");
                if ("getValue" .equalsIgnoreCase(cmds[0])) {
                    Object obj = CacheUtils.getValue(cmds[1]);
                    System.out.printf("[%s]=>%s\n", cmds[1], obj);
                } else if ("get" .equalsIgnoreCase(cmds[0])) {
                    CacheObject obj = CacheUtils.get(cmds[1]);
                    System.out.printf("[%s,%s,L%d]=>%s\n", obj.getRegion(), obj.getKey(), obj.getLevel(), obj.getValue());
                } else if ("set" .equalsIgnoreCase(cmds[0])) {
                    CacheUtils.set(cmds[1], cmds[2]);
                    System.out.printf("[%s]<=%s\n", cmds[1], cmds[2]);
                } else if ("evict" .equalsIgnoreCase(cmds[0])) {
                    CacheUtils.evict(cmds[1]);
                    System.out.printf("[%s]=>null\n", cmds[1]);
                } else if ("clear" .equalsIgnoreCase(cmds[0])) {
                    CacheUtils.clear(cmds[1]);
                    System.out.printf("Cache [%s] clear.\n", cmds[1]);
                } else if ("help" .equalsIgnoreCase(cmds[0])) {
                    printHelp();
                } else {
                    System.out.println("Unknown command.");
                    printHelp();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong arguments.");
                printHelp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
        CacheUtils.close();
        System.exit(0);
    }

    private static void printHelp() {
        System.out.println("Usage: [cmd] region key [value]");
        System.out.println("cmd: get/getValue/set/evict/quit/exit/help");
    }
}