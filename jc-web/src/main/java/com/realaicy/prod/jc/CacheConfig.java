package com.realaicy.prod.jc;

import com.realaicy.prod.jc.common.properties.StudyProperties;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SizeOfPolicyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * The type Jc web application.
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties({StudyProperties.class})
@Profile({StaticParams.SPRINGPROFILES.PRODUCTION, StaticParams.SPRINGPROFILES.DEVELOP})
public class CacheConfig extends CachingConfigurerSupport {

    @Override
    @Bean
    public CacheManager cacheManager() {

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        SizeOfPolicyConfiguration sizeOfPolicyConfiguration = new SizeOfPolicyConfiguration();
        sizeOfPolicyConfiguration.setMaxDepthExceededBehavior("abort");
//        sizeOfPolicyConfiguration.setMaxDepth(StaticParams.REALCACHE.DEFAULTSIZE);
        config.setMaxBytesLocalHeap(StaticParams.REALCACHE.DEFAULTTOTALBYTES);
        config.sizeOfPolicy(sizeOfPolicyConfiguration);

        CacheConfiguration defaultCacheConfiguration = new CacheConfiguration();
        defaultCacheConfiguration.setName("realdefaultcache");
        defaultCacheConfiguration.setEternal(false);
        defaultCacheConfiguration.setMaxBytesLocalHeap(StaticParams.REALCACHE.DEFAULTBYTES);
        defaultCacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        config.addCache(defaultCacheConfiguration);

        CacheConfiguration wUsermenuCacheConfiguration = new CacheConfiguration();
        wUsermenuCacheConfiguration.setName("wUsermenu");
        wUsermenuCacheConfiguration.setEternal(false);
        defaultCacheConfiguration.setMaxBytesLocalHeap(StaticParams.REALCACHE.DEFAULTBYTES);
        wUsermenuCacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        config.addCache(wUsermenuCacheConfiguration);


        CacheConfiguration wUserSecCacheConfiguration = new CacheConfiguration();
        wUserSecCacheConfiguration.setName("User");
        wUserSecCacheConfiguration.setEternal(false);
        wUserSecCacheConfiguration.setMaxBytesLocalHeap(StaticParams.REALCACHE.DEFAULTBYTES);
        wUserSecCacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        config.addCache(wUserSecCacheConfiguration);


        return new EhCacheCacheManager(net.sf.ehcache.CacheManager.newInstance(config));
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean
    public CacheResolver runtimeCacheResolver() {
        return new CacheConfig.RuntimeCacheResolver(cacheManager());
    }

    /**
     * Example of {@link CacheResolver} that resolve the caches at
     * runtime (i.e. based on method invocation parameters).
     * <p>Expects the second argument to hold the name of the cache to use
     */
    private static class RuntimeCacheResolver extends AbstractCacheResolver {

        private Logger logger = LoggerFactory.getLogger(RuntimeCacheResolver.class);


        private RuntimeCacheResolver(CacheManager cacheManager) {
            super(cacheManager);
        }

        @Override
        protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {

//            Set<String> cacheNames = context.getOperation().getCacheNames();

            if (context.getMethod().getName().equals("findUserMenu")) {
                return Collections.singleton("wUsermenu");
            } else {
                String cacheName = context.getTarget().getClass().getSimpleName();
                return Collections.singleton(cacheName);
            }


        }

        @Override
        public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
            Collection<String> cacheNames = getCacheNames(context);
            if (cacheNames == null) {
                return Collections.emptyList();
            } else {
                Collection<Cache> result = new ArrayList<>();
                for (String cacheName : cacheNames) {
                    Cache cache = this.getCacheManager().getCache(cacheName);
                    if (cache == null) {
                        //realaicy modified!
                        logger.warn("Cannot find cache named :"
                                + " {} for {},so realaicy try to use default", cacheName, context.getOperation());
                        cache = this.getCacheManager().getCache("realdefaultcache");
//                        throw new IllegalArgumentException("Cannot find cache named '" +
//                                cacheName + "' for " + context.getOperation());
                    }
                    result.add(cache);
                }
                return result;
            }
        }
    }
}
