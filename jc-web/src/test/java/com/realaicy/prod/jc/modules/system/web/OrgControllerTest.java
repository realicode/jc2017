package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.test.JcWebRootTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrgController.class)
@Ignore
public class OrgControllerTest extends JcWebRootTest {


    private static final String baseUri = "/system/org/";

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrgService orgService;


    @Before
    public void init() {

        List<Org> orgList = new ArrayList<>();
        Org orgA = new Org();
        orgA.setName("test_web_a");
        orgA.setType("6");
        orgA.setNameAlias1("1");
        orgA.setRegion("1");
        orgA.setRegion("1");
        orgA.setProvince("1");
        orgA.setContactName("1");
        orgA.setStatus(Short.valueOf("1"));
        orgA.setDeleteFlag(false);
        orgA.setCreaterID(BigInteger.valueOf(6L));
        orgA.setUpdaterID(BigInteger.valueOf(6L));
        orgA.setCreateTime(new Date());
        orgA.setUpdateTime(new Date());

        Org orgB = new Org();
        orgB.setName("test_web_b");
        orgB.setType("6");
        orgB.setNameAlias1("1");
        orgB.setRegion("1");
        orgB.setRegion("1");
        orgB.setProvince("1");
        orgB.setContactName("1");
        orgB.setStatus(Short.valueOf("1"));
        orgB.setDeleteFlag(false);
        orgB.setCreaterID(BigInteger.valueOf(6L));
        orgB.setUpdaterID(BigInteger.valueOf(6L));
        orgB.setCreateTime(new Date());
        orgB.setUpdateTime(new Date());

        orgList.add(orgA);
        orgList.add(orgB);


        //noinspection unchecked
        given(this.orgService.findAll(any(Specification.class), any(PageRequest.class))).willReturn(orgList);
        //noinspection unchecked
        given(this.orgService.count(any(Specification.class))).willReturn(2L);
        given(this.orgService.count()).willReturn(2L);
    }

    @Test
    public void getPage() throws Exception {

        this.mockMvc.perform(get(baseUri + "page").
                with(user(userSuserAdmin))).andExpect(status().isOk());
        this.mockMvc.perform(get(baseUri + "page").
                with(user(userSecretaryWym))).andExpect(status().isOk());
        this.mockMvc.perform(get(baseUri + "page").
                with(user(userDirector))).andExpect(status().isOk());
        this.mockMvc.perform(get(baseUri + "page").
                with(user(userSponsor))).andExpect(status().isOk());
    }

    @Test
    public void postList4dt() throws Exception {

        this.mockMvc.perform(post(baseUri + "list4dt").
                param("start", "0").param("length", "30").param("order[0][column]", "1").param("order[0][dir]", "asc").param("length", "30").param("length", "30").
                with(user(userSuserAdmin)).with(csrf())).andExpect(status().isOk()).andDo(print());
    }

}
