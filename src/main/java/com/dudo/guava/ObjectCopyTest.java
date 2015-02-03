package com.dudo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zkai on 2014/9/12.
 */
public class ObjectCopyTest {
    static LoadingCache<String, Map> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
            .build(
                    new CacheLoader<String, Map>() {
                        public Map load(String key) {
                            return null;
                        }
                    }
            );

    public static void main(String[] args) {
        HashMap<Object, Object> hashMapOrg = new HashMap<>();
        cache.put("test", hashMapOrg);

        Map test = cache.getUnchecked("test");
        test.put("key", "val");

        System.out.println(test.size());
        System.out.println(hashMapOrg.size());
    }


}
