package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcWebApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by realaicy on 2017/3/19.
 * xx
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = JcWebApplication.class)
public abstract class JcDataBasicRootTest {
}
