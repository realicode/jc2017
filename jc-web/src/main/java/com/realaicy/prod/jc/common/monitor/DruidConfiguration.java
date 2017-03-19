package com.realaicy.prod.jc.common.monitor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by realaicy on 2017/2/13.
 * xxx
 */
@Configuration
@Profile({StaticParams.SPRINGPROFILES.PRODUCTION, StaticParams.SPRINGPROFILES.DEVELOP})
public class DruidConfiguration {
    /**
     * 注册一个StatViewServlet
     * @return xxx
     *
     */
    @Bean
    public ServletRegistrationBean druidStatViewServle2() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return xx
     *
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
