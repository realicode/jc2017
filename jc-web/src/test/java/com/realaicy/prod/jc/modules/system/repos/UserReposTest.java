package com.realaicy.prod.jc.modules.system.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserReposTest {

    @Autowired
    private UserRepos userRepos;
    @Test
    public void findByUsername() throws Exception {
        assertThat(userRepos.findByUsername("realaicy").getOrg().getName()).isEqualTo("虚拟机构");
    }

    @Test
    public void findFirstNoDeletedUserIDByRoleIDNative() throws Exception {
        assertThat(userRepos.findFirstNoDeletedUserIDByRoleIDNative(BigInteger.valueOf(7L))).isNotNull();
    }

}