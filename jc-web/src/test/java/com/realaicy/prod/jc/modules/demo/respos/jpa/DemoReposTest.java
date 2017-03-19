package com.realaicy.prod.jc.modules.demo.respos.jpa;

import com.realaicy.prod.jc.modules.demo.model.DemoEntity;
import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by realaicy on 2017/2/13.
 * xxx
 */
public class DemoReposTest extends JcDataRootTest {

    @Autowired
    private
    DemoRepos demoRepos;

    @Test
    public void findByUsername() throws Exception {

        DemoEntity demoEntity = demoRepos.findOne(BigInteger.valueOf(6));
        assertNotNull(demoEntity);
        assertThat(demoEntity.getUsername()).isEqualTo("realaicy");
    }

}