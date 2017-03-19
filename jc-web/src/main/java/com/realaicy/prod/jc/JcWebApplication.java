package com.realaicy.prod.jc;

import com.realaicy.prod.jc.common.RealBaseMapper;
import com.realaicy.prod.jc.common.properties.StudyProperties;
import com.realaicy.prod.jc.lib.core.data.jpa.SimpleBaseJPARepository;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.security.SessionCounterListener;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SizeOfPolicyConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * The type Jc web application.
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseJPARepository.class)
@MapperScan(basePackages = "com.realaicy.prod.jc.modules.demo.respos.mybatis",
        markerInterface = RealBaseMapper.class)
@EnableAspectJAutoProxy
@EnableConfigurationProperties({StudyProperties.class})
@Profile({StaticParams.SPRINGPROFILES.PRODUCTION, StaticParams.SPRINGPROFILES.DEVELOP})
public class JcWebApplication {

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionCounterListener();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JcWebApplication.class, args);
    }
}
