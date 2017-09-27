package com.edward.io.third.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.*;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.net.URL;

/**
 * Created by edwardcheng on 2017/9/18.
 */
public class EhcacheExample {

    private static final String CACHE = "sampleCache";
    private static CacheManager cacheManager;

    public EhcacheExample() {
        init();
    }

    private static void init() {
        Configuration config = new Configuration();
        DiskStoreConfiguration dsc = new DiskStoreConfiguration();
        dsc.setPath("ehcache.io.temp");
        config.addDiskStore(dsc);

        CacheConfiguration defaultCache = new CacheConfiguration();
        defaultCache.setMaxEntriesLocalHeap(10000L);
        defaultCache.setEternal(false);
        defaultCache.setTimeToIdleSeconds(600L);
        defaultCache.addPersistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP.name()));
        config.addDefaultCache(defaultCache);

        CacheConfiguration sampleCache = new CacheConfiguration();
        sampleCache.setName(CACHE);
        sampleCache.setMaxEntriesLocalHeap(100L);
        sampleCache.setEternal(false);
        sampleCache.setTimeToLiveSeconds(30L);
        sampleCache.setTimeToIdleSeconds(30L);
        sampleCache.addPersistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP.name()));
        sampleCache.setMemoryStoreEvictionPolicyFromObject(MemoryStoreEvictionPolicy.LFU);
        config.addCache(sampleCache);

        FactoryConfiguration factoryConfig = new FactoryConfiguration();
        factoryConfig.className(DefaultCacheManagerEventListenerFactory.class.getCanonicalName());
        config.addCacheManagerEventListenerFactory(factoryConfig);

        if (cacheManager == null) {
            cacheManager = new CacheManager(config);
        }

    }

    /**
     * init cachemanager form resource
     * @param resource
     */
    private static void init(String resource) {
        URL url = EhcacheExample.class.getResource("/com/edward/io/third/ehcache" + resource);
        if (cacheManager != null) {
            cacheManager = new CacheManager(url);
        }
    }


    /**
     * stop cachemanager
     */
    public void destroy() {
        cacheManager.shutdown();
        System.out.println("cachemanager has been shut down");
    }

    public boolean save(String key, Object val) {
        Cache cache = cacheManager.getCache(CACHE);
        Element old = cache.get(key);
        if (old != null) {
            cache.remove(key);
        }
        cache.put(new Element(key, val));
        return true;
    }

    public boolean remove(String key) {
        Cache cache = cacheManager.getCache(CACHE);
        Element old = cache.get(key);
        if (old != null) {
            cache.remove(key);
            return true;
        }
        return false;
    }

    public Object find(String key) {
        Cache cache = cacheManager.getCache(CACHE);
        Element old = cache.get(key);
        if (old != null) {
            return old.getObjectValue();
        }
        return null;
    }

    public boolean clear() {
        Cache cache = cacheManager.getCache(CACHE);
        cache.removeAll();
        return true;
    }

    public void cacheInfo() {
        Cache cache = cacheManager.getCache(CACHE);
        System.out.println(String.format("Number of cache items: %d", cache.getSize()));
        System.out.println(String.format("Number of cache items in memory: %d", cache.getMemoryStoreSize()));
        System.out.println(String.format("Number of cache items on disk: %d", cache.getDiskStoreSize()));
    }

    public static void main(String[] args) {

        EhcacheExample ehcache = new EhcacheExample();

        int size = 2000;
        for (int i = 0; i < size; i++) {
            ehcache.save("key"+i, "test"+i);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(ehcache.find("key"+i));
        }
        ehcache.cacheInfo();
        ehcache.clear();
        ehcache.cacheInfo();
        ehcache.destroy();

    }
}
