package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.JcITTestContext;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.test.JcITRootTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Ignore
public  class OrgITTest extends JcITRootTest{

    private static final String baseUri = "/system/org/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void aaa() throws Exception {

        this.mockMvc.perform(get(baseUri+ "page").with(user(userSuserAdmin))).andExpect(status().isOk());
    }

}
