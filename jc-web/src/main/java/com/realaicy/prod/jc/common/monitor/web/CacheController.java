package com.realaicy.prod.jc.common.monitor.web;


import com.realaicy.prod.jc.common.monitor.model.MCache;
import net.sf.ehcache.Cache;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CacheController {

    private static Logger logger = LoggerFactory.getLogger(CacheController.class);


    private final AtomicLong counter = new AtomicLong();

    private final CacheManager cacheManager;

    @Autowired
    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @RequestMapping("/m/cache")
    public MCache getCacheInfo() {

        logger.info("cache num:{}", cacheManager.getCacheNames().size());

        MCache mCache = new MCache();
        mCache.setSum(cacheManager.getCacheNames().size());

        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = (Cache) cacheManager.getCache(cacheName).getNativeCache();
            logger.info("cache name:{}", cacheName);
            logger.info("cache size:{}", cache.getSize());
            logger.info("cache list:{}", cache.getKeys());
            StatisticsGateway statistics = cache.getStatistics();

            logger.info("statistics size:{}", statistics.getSize());
            logger.info("statistics getLocalDiskSize:{}", statistics.getLocalDiskSize());
            logger.info("statistics getLocalHeapSize:{}", statistics.getLocalHeapSize());
            logger.info("statistics getLocalHeapSizeInBytes:{}", statistics.getLocalHeapSizeInBytes());

        }

        return mCache;
    }

    @RequestMapping("/m/cache/realmenu")
    public void getMenuInfo() {


        Cache cache = (Cache) cacheManager.getCache("realmenu").getNativeCache();
        logger.info("cache name:{}", "realmenu");
        logger.info("cache size:{}", cache.getSize());
        logger.info("cache key list:{}", cache.getKeys());

        StatisticsGateway statistics = cache.getStatistics();

        logger.info("statistics size:{}", statistics.getSize());
        logger.info("statistics getLocalDiskSize:{}", statistics.getLocalDiskSize());
        logger.info("statistics getLocalHeapSize:{}", statistics.getLocalHeapSize());
        logger.info("statistics getLocalHeapSizeInBytes:{}", statistics.getLocalHeapSizeInBytes());

    }
}
