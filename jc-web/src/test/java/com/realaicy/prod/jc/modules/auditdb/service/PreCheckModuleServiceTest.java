package com.realaicy.prod.jc.modules.auditdb.service;

import com.realaicy.prod.jc.test.JcDataRootTest;
import com.realaicy.prod.jc.test.JcServiceRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by realaicy on 2017/5/13.
 * xx
 */
public class PreCheckModuleServiceTest extends JcServiceRootTest {

    @Autowired
    private PreCheckModuleService preCheckModuleService;

    @Test
    public void existTemName() throws Exception {
        System.out.println(preCheckModuleService.existTemName("aa"));
    }

}