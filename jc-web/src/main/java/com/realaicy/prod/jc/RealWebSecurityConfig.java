package com.realaicy.prod.jc;


import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.PATHREGX.*;


/**
 * Created by Realaicy on 2015/5/.
 * XX
 */
@Configuration
@Profile({StaticParams.SPRINGPROFILES.PRODUCTION, StaticParams.SPRINGPROFILES.DEVELOP,
        StaticParams.SPRINGPROFILES.TEST_UAT, StaticParams.SPRINGPROFILES.TEST_IT})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class RealWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public RealWebSecurityConfig(@Qualifier("R2") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(StaticParams.REALNUM.N10);
    }

    private final UserDetailsService userDetailsService;


//    /**
//     * 自定义的AuthenticationProvider
//     */
//    @Autowired
//    private RealAuthenticationProvider realAuthenticationProvider;


    /**
     * Configure void.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(SB_ALL, REALAICY_ALL, REALRES, SIGNUP, SIGNUP_CHECKUSERNAME, SIGNUP_SENTMBCODE,
                        StaticParams.PATHREGX.STATIC, StaticParams.PATHREGX.TEMP_TEST, "/runtime/tasks/**").permitAll()//无需访问权限
                .antMatchers(StaticParams.PATHREGX.AUTHADMIN).hasAuthority(StaticParams.USERROLE.ROLE_ADMIN)//admin角色访问权限
                .antMatchers(StaticParams.PATHREGX.AUTHUSER).hasAuthority(StaticParams.USERROLE.ROLE_USER)//user角色访问权限
                .anyRequest()//all others request authentication
                .authenticated()
                //.and().exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"))
                .and()
                .formLogin().loginPage("/login").permitAll().and().exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().permitAll().and().sessionManagement().invalidSessionUrl("/g/realerror/session/realinvalid")
                .maximumSessions(1).expiredUrl("/g/realerror/session/expire");
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers(SIGNUP, REGIS, REALRES); // APIs use a key
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        //auth.authenticationProvider(realAuthenticationProvider);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
