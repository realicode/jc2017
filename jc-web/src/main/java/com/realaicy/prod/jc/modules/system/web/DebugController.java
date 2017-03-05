package com.realaicy.prod.jc.modules.system.web;

import net.sf.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
public class DebugController {

    private Logger logger = LoggerFactory.getLogger(DebugController.class);


    private final CacheManager cacheManager;

    @Autowired
    public DebugController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @RequestMapping(value = "/debug/temp", method = RequestMethod.GET)
    public String initDebugPage() {
        return "debug/temp";
    }

    @RequestMapping(value = "/debug/cache/clear", method = RequestMethod.GET)
    @ResponseBody
    public String clearCache() {

        logger.info("In Clear Cache");

        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = (Cache) cacheManager.getCache(cacheName).getNativeCache();
            cache.removeAll();
        }

        return "OK";
    }
}