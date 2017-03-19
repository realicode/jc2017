package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcWebTestContext;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.modules.system.web.LoginController;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

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

    protected static RealUserDetails userSuserAdmin;
    protected static RealUserDetails userSecretaryWym;
    protected static RealUserDetails userDirector;
    protected static RealUserDetails userSponsor;

    @BeforeClass
    public static void setup() {
        Collection<GrantedAuthority> grantedAuthoritiesForSuperAdmin =
                AuthorityUtils.commaSeparatedStringToAuthorityList("超级管理员");   //超级管理员
        Collection<GrantedAuthority> grantedAuthoritiesForSecretaryWym =
                AuthorityUtils.commaSeparatedStringToAuthorityList("秘书处处长,项目经理");   //秘书长
        Collection<GrantedAuthority> grantedAuthoritiesForDirector =
                AuthorityUtils.commaSeparatedStringToAuthorityList("肿瘤协作组主任");       //协作组主任
        Collection<GrantedAuthority> grantedAuthoritiesForSponsor =
                AuthorityUtils.commaSeparatedStringToAuthorityList("潜在申办者");    //申办者

        HashSet<String> realAuthoritiesForSuperAdmin = new HashSet<>();
        HashSet<String> realAuthoritiesForSecretaryWym = new HashSet<>();
        HashSet<String> realAuthoritiesForDirector = new HashSet<>();
        HashSet<String> realAuthoritiesForSponsor = new HashSet<>();

        Collections.addAll(realAuthoritiesForSuperAdmin, "superadmin");
        Collections.addAll(realAuthoritiesForSecretaryWym,
                "superop,Org-r,Org-c,Role-a,User-a,DocRes-a,DocFileRes-a,Appliance-a,Appliance-ack,MyWork-r".split(","));
        Collections.addAll(realAuthoritiesForDirector,
                "Appliance-r,Appliance-approve,MyWork-r".split(","));
        Collections.addAll(realAuthoritiesForSponsor,
                "Appliance-a".split(","));

        userSuserAdmin = new RealUserDetails(BigInteger.valueOf(6), "realaicy", "fakepassword",
                "fakenickname", true, true, true, true,
                grantedAuthoritiesForSuperAdmin, realAuthoritiesForSuperAdmin);
        userSecretaryWym = new RealUserDetails(BigInteger.valueOf(6), "wym", "fakepassword",
                "fakenickname", true, true, true, true,
                grantedAuthoritiesForSecretaryWym, realAuthoritiesForSecretaryWym);
        userDirector = new RealUserDetails(BigInteger.valueOf(6), "zhaoy", "fakepassword",
                "fakenickname", true, true, true, true,
                grantedAuthoritiesForDirector, realAuthoritiesForDirector);
        userSponsor = new RealUserDetails(BigInteger.valueOf(6), "sbztest1", "fakepassword",
                "fakenickname", true, true, true, true,
                grantedAuthoritiesForSponsor, realAuthoritiesForSponsor);
    }


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
