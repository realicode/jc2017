package com.realaicy.prod.jc.modules.system.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrgReposTest {
    @Autowired
    private OrgRepos orgRepos;
    @Test
    public void findByRegion() throws Exception {
        assertThat(orgRepos.findByRegion("华北").size()).isEqualTo(1);

    }

    @Test
    public void findByProvince() throws Exception {
        assertThat(orgRepos.findByProvince("北京市").size()).isEqualTo(1);
        assertThat(orgRepos.findByProvince("西藏").size()).isEqualTo(0);

    }

}