//package com.dudo.ehcache;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;
//import net.sf.ehcache.statistics.StatisticsGateway;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by zkai on 2014/9/12.
// */
//public class ObjectCopyTest {
//    public static void main(String[] args) throws InterruptedException {
////        CacheManager ehCacheManager = new CacheManager();
////        Cache<String,Map> cache = ehCacheManager.getCache("test");
//
//        CacheManager singletonManager = CacheManager.create();
//        Cache test2 = new Cache("testCache2", 1, true, false, 5, 2);
//        singletonManager.addCache(test2);
//        Cache memoryOnlyCache = singletonManager.getCache("testCache2");
//
//        StatisticsGateway statistics = test2.getStatistics();
//        p(statistics);
//        memoryOnlyCache.put(new Element("a", "b"));
//        p(statistics);
//
//        HashMap<Object, Object> hashMapOrg = new HashMap<>();
//
//        Element element = new Element("test", hashMapOrg);
//        memoryOnlyCache.put(element);
//        p(statistics);
//        memoryOnlyCache.put(new Element("a", "b"));
//
//        // ...给ehcache 一个时间,以便当overflowToDisk时, 将内存数据刷到硬盘上.
//        Thread.sleep(1000);
//        p(statistics);
//
//        Element test = memoryOnlyCache.get("test");
//        Map objectValue = (Map)test.getObjectValue();
//        objectValue.put("e", "v");
//        p(statistics);
//        System.out.println(objectValue.size());
//        System.out.println(hashMapOrg.size());
//        CacheManager.getInstance().shutdown();
//    }
//
//    static void  p(StatisticsGateway s) {
//        System.out.println("getLocalHeapSize:"+s.getLocalHeapSize());
//        System.out.println("getLocalDiskSize"+s.getLocalDiskSize());
//        System.out.println("getLocalOffHeapSize"+s.getLocalOffHeapSize());
//    }
//}
