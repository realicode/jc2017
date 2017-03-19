package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcUATTestContext;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcUATTestContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(StaticParams.SPRINGPROFILES.TEST_UAT)
public  class JcUATRootTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void aaa() throws Exception {


        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/signup",
                String.class)).contains("用户注册");
//        this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("用户注册")));
    }
//	@Test
//	public void contextLoads() {
//	}

}
