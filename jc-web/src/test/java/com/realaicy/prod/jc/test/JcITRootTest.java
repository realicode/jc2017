package com.realaicy.prod.jc.test;

import com.realaicy.prod.jc.JcITTestContext;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcITTestContext.class)
@AutoConfigureMockMvc
@ActiveProfiles(StaticParams.SPRINGPROFILES.TEST_IT)
public abstract class JcITRootTest {

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

  /*  @Test
    public void aaa() throws Exception {

        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("用户注册")));
    }*/

}
