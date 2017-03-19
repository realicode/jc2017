package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcServiceTestContext;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcServiceTestContext.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(StaticParams.SPRINGPROFILES.TEST_SERVICE)
public abstract class JcServiceRootTest {

//	@Test
//	public void contextLoads() {
//	}

}
