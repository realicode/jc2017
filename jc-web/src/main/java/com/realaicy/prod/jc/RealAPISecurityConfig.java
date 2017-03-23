package com.realaicy.prod.jc;


import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * Created by Realaicy on 2015/5/.
 * XX
 */
@Configuration
@Profile({StaticParams.SPRINGPROFILES.PRODUCTION, StaticParams.SPRINGPROFILES.DEVELOP,
        StaticParams.SPRINGPROFILES.TEST_UAT, StaticParams.SPRINGPROFILES.TEST_IT})
@Order(1)
public class RealAPISecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * Configure void.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.antMatcher("/wx/**").csrf().disable().cors().disable().sessionManagement().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        // @formatter:off
        http.antMatcher("/wx/**").csrf().disable().cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/wx/**", "/g/realerror/**").permitAll();
        // @formatter:on
    }

}
