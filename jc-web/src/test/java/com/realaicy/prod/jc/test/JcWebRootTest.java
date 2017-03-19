package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcWebTestContext;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.modules.system.web.LoginController;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = JcWebTestContext.class)
//@AutoConfigureMockMvc(secure=false)
@ActiveProfiles(StaticParams.SPRINGPROFILES.TEST_WEB)
@Import(SecurityTestConfig.class)

public abstract class JcWebRootTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private UserInfoService userInfoService;
//    @MockBean
//    private OrgService orgService;
//    @MockBean
//    private UserService userService;
//    @MockBean
//    private PasswordEncoder bcryptEncoder;


   /* @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();
    }*/

   /* @Test
    public void aaa() throws Exception {
        this.mockMvc.perform(get("/signup").
                with(user("raghu").roles("superadmin"))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("用户注册")));
    }*/

}
