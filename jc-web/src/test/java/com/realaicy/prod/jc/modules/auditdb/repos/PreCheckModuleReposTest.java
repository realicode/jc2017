package com.realaicy.prod.jc.modules.auditdb.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/5/13.
 * xxx
 */
public class PreCheckModuleReposTest extends JcDataRootTest {

    @Autowired
    private PreCheckModuleRepos preCheckModuleRepos;

    @Test
    public void checkTemName() throws Exception {
        System.out.println(preCheckModuleRepos.checkTemName("aa"));
    }

    @Test
    public void checkTemID() throws Exception {
        System.out.println(preCheckModuleRepos.findByTemAndRootlevel(BigInteger.valueOf(149),true));
    }


    @Test
    public void temNameList() {
        System.out.println(preCheckModuleRepos.temNameList().size());
    }


}