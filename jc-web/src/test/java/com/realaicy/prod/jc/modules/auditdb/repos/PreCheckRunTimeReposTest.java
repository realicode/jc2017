package com.realaicy.prod.jc.modules.auditdb.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/6/19.
 * xxx
 */
public class PreCheckRunTimeReposTest extends JcDataRootTest {

    @Autowired
    private PreCheckRunTimeRepos preCheckRunTimeRepos;

    @Test
    public void findID() throws Exception {
        System.out.println(preCheckRunTimeRepos.findID(BigInteger.valueOf(7), BigInteger.valueOf(181)));
    }

}