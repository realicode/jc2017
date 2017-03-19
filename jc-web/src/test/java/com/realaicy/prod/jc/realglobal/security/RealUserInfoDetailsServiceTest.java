package com.realaicy.prod.jc.realglobal.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by realaicy on 2017/2/26.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RealUserInfoDetailsServiceTest {
    @Autowired
    private
    RealUserDetailsService realUserDetailsService;

    @Test
    public void loadUserByUsername() throws Exception {
        UserDetails realUserDetails = realUserDetailsService.loadUserByUsername("realaicy");
        assertThat(realUserDetails.getUsername()).isEqualTo("realaicy");

    }

}