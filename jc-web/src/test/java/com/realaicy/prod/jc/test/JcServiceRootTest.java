package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcServiceTestRootContext;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcServiceTestRootContext.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("realtest")
public abstract class JcServiceRootTest {

//	@Test
//	public void contextLoads() {
//	}

}
