package com.realaicy.prod.jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by realaicy on 16/8/22.
 * xxx
 */
@Configuration
public class RealWebMvcConfig extends WebMvcConfigurerAdapter {

    private final HandlerInterceptor logger;

    @Autowired
    public RealWebMvcConfig(HandlerInterceptor logger) {
        this.logger = logger;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logger);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/realres/**").addResourceLocations("classpath:/static/realres/").setCachePeriod(600);
    }
}
