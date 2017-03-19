package com.realaicy.prod.jc.modules.system.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class OrgReposTest {
    @Autowired
    private OrgRepos orgRepos;
    @Test
    public void findByRegion() throws Exception {
        assertThat(orgRepos.findByRegion("华北地区")).extracting("name")
                .contains("天津市肿瘤医院").doesNotContain("上海肺科医院");
    }

    @Test
    public void findByProvince() throws Exception {
        assertThat(orgRepos.findByProvince("北京")).extracting("name")
                .contains("北京肿瘤医院").doesNotContain("天津市肿瘤医院");
    }

}