package com.realaicy.prod.jc.modules.system.service;

import com.realaicy.prod.jc.test.JcServiceRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by realaicy on 2017/3/18.
 * xxx
 */
public class OrgServiceTest extends JcServiceRootTest{

    @Autowired
    private OrgService orgService;

    @Test
    public void checkOrgName() {
        assertThat(orgService.checkOrgName("天津市肿瘤医院")).isTrue();
        assertThat(orgService.checkOrgName("不存在的医院")).isFalse();
    }

    @Test
    public void canBeDelete(){
        assertThat(orgService.canBeDelete(BigInteger.valueOf(82))).isFalse();
        assertThat(orgService.canBeDelete(BigInteger.valueOf(113))).isFalse();
        assertThat(orgService.canBeDelete(BigInteger.valueOf(80))).isTrue();

    }

}