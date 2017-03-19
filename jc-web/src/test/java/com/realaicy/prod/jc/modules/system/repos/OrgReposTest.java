package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class OrgReposTest extends JcDataRootTest{
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