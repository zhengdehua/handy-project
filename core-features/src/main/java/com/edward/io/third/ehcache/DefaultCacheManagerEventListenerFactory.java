package com.edward.io.third.ehcache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

import java.util.Properties;

public class DefaultCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {

        @Override
        public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
            return new CacheManagerEventListener() {
                @Override
                public void init() throws CacheException {
                    System.out.println("initialize cache listener");
                }

                @Override
                public Status getStatus() {
                    return Status.STATUS_ALIVE;
                }

                @Override
                public void dispose() throws CacheException {
                    System.out.println("stop cache listener");
                }

                @Override
                public void notifyCacheAdded(String cacheName) {
                    System.out.println(String.format("cache key [%s] added", cacheName));
                }

                @Override
                public void notifyCacheRemoved(String cacheName) {
                    System.out.println(String.format("cache key [%s] removed", cacheName));
                }
            };
        }
    }