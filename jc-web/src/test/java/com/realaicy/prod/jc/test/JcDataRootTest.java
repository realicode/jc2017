package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.CacheConfig;
import com.realaicy.prod.jc.JcWebApplication;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by realaicy on 2017/3/19.
 * xx
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = JcWebApplication.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = StaticParams.SPRINGPROFILES.DEVELOP)
@Import(CacheConfig.class)
public abstract class JcDataRootTest {
}
