package com.realaicy.prod.jc.realglobal.security;

import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.service.MenuService;
import com.realaicy.prod.jc.modules.system.web.IndexController;
import com.realaicy.prod.jc.test.JcWebRootTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = IndexController.class)
public class SecurityWebTest extends JcWebRootTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  MenuService menuService;
    @MockBean
    private  MyWorkService myWorkService;

    @Test
    @Ignore
    public void unauthorizedUserShowReturnToLoginPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location",containsString("login")));
    }

    @Test
    @Ignore
    public void authorizedUserShowReturnToIndexPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location",containsString("login")));
    }
}
