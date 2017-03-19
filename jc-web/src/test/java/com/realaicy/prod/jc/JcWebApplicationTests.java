package com.realaicy.prod.jc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcServiceTestRootContext.class)
@ActiveProfiles("realtest")
public class JcWebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
