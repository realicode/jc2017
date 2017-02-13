package com.realaicy.prod.jc.modules.demo.respos.jpa;

import com.realaicy.prod.jc.modules.demo.model.DemoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by realaicy on 2017/2/13.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class DemoReposTest {

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