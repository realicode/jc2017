package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.PATHREGX.*;

/**
 * Created by realaicy on 2017/3/19.
 * xx
 */
@TestConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTestConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(SB_ALL, REALAICY_ALL, REALRES,
                SIGNUP, SIGNUP_CHECKUSERNAME, SIGNUP_SENTMBCODE,
                WX_TEST, WX_TEST2, WX_TEST3, WX,
                StaticParams.PATHREGX.STATIC, StaticParams.PATHREGX.TEMP_TEST, "/runtime/tasks/**").permitAll()
                .antMatchers(StaticParams.PATHREGX.AUTHADMIN).hasAuthority(StaticParams.USERROLE.ROLE_ADMIN)//admin角色访问权限
                .antMatchers(StaticParams.PATHREGX.AUTHUSER).hasAuthority(StaticParams.USERROLE.ROLE_USER) //user角色访问权限
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and().exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/wx/test", SIGNUP, REGIS); // APIs use a key
    }

    @Bean
    public UserDetailsService userDetailsService() {

        GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");

        UserDetails user1 = (UserDetails) new User("raghu", "pwd", Arrays.asList(adminAuthority));
        UserDetails user2 = (UserDetails) new User("ram", "pwd", Arrays.asList(userAuthority));

        return new InMemoryUserDetailsManager(Arrays.asList(user1, user2));
    }
}
