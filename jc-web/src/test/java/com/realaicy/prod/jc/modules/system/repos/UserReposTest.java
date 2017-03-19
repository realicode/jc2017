package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by realaicy on 2017/3/19.
 * xxx
 */
public class UserReposTest  extends JcDataRootTest {
    @Autowired
    private  UserRepos userRepos;

    @Test
    public void checkUsernameTest() throws Exception {
        assertThat(userRepos.checkUsername("realetest1")).isEqualTo(0);
        assertThat(userRepos.checkUsername("realaicy")).isEqualTo(1);

    }

    @Test
    public void findFirstNoDeletedUserIDByOrgIDNativeTest() throws Exception {
        assertThat(userRepos.findFirstNoDeletedUserIDByOrgIDNative(BigInteger.valueOf(113))).isNotNull();
        assertThat(userRepos.findFirstNoDeletedUserIDByOrgIDNative(BigInteger.valueOf(82))).isNotNull();

        assertThat(userRepos.findFirstNoDeletedUserIDByOrgIDNative(BigInteger.valueOf(88))).isNull();

    }

    @Test
    public void findFirstNoDeletedUserIDByRoleIDNativeTest() throws Exception {
        assertThat(userRepos.findFirstNoDeletedUserIDByRoleIDNative(BigInteger.valueOf(7))).isNotNull();
        assertThat(userRepos.findFirstNoDeletedUserIDByRoleIDNative(BigInteger.valueOf(6))).isNotNull();

        assertThat(userRepos.findFirstNoDeletedUserIDByRoleIDNative(BigInteger.valueOf(2))).isNull();

    }



}