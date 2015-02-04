package com.dudo.hotswap.server.classloader;

import com.dudo.hotswap.server.UpgradeNotifier;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zkai on 2015/2/4.
 */
public class HotswapDeploy {
    private String libDir;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private volatile boolean isStop = false;
    private static CustomClassLoader currCustomClassLoader = null;
    private final byte[] currCustomClassLoaderLock = new byte[0];

    public HotswapDeploy(String libDir) {
        this.libDir = libDir;
    }

    public void modifyLibDir(String libDir) {
        this.libDir = libDir;
    }

    private volatile HashMap<String, String> currAllFiles = new HashMap<>();

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!isStop) {
                loadClasses();
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    /**
     * 1. 遍历文件（仅一级目录。不会扫描二级目录），并计算md5值
     * 2. 比较差异。如果有差异则更新，没差异不处理。
     * 3. 更新class。并通知客户端更新完成。
     */
    private void loadClasses() {
        System.out.println("HotswapDeploy-Timer......");
        String libDir1 = HotswapDeploy.this.libDir;
        File dir = new File(libDir1);
        HashMap<String, String> currFiles = new HashMap<>();
        try {
            if (dir.isFile()) {
                try (FileInputStream data = new FileInputStream(dir);) {
                    currFiles.put(dir.getCanonicalPath().intern(), DigestUtils.md5Hex(data));
                }
            } else if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            try (FileInputStream data = new FileInputStream(file)) {
                                currFiles.put(file.getCanonicalPath().intern(), DigestUtils.md5Hex(data));
                            }
                        }
                    }
                }
            }

            // 比较差异。
            boolean diff = false;
            final HashMap<String, String> prevFiles = HotswapDeploy.this.currAllFiles;

            if (prevFiles.size() != currFiles.size()) {
                diff = true;
            } else {
                final MapDifference<String, String> difference = Maps.difference(prevFiles, currFiles);
                if (!difference.entriesOnlyOnRight().isEmpty()
                        || !difference.entriesOnlyOnLeft().isEmpty()
                        || !difference.entriesDiffering().isEmpty()) {
                    diff = true;
                }
            }

            if (diff) {
                HotswapDeploy.this.currAllFiles = currFiles;
                synchronized (currCustomClassLoaderLock) {
                    currCustomClassLoader = new CustomClassLoader(libDir);
                }
                UpgradeNotifier.notifyClient("Hotswap completed.");
                System.out.println("HotswapDeploy completed.....");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static CustomClassLoader getCurrCustomClassLoader() {
        return currCustomClassLoader;
    }

    public void shutdown() {
        isStop = true;
        scheduledExecutorService.shutdown();
    }
}
