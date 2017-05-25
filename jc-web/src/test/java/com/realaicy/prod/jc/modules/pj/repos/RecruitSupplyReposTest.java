package com.realaicy.prod.jc.modules.pj.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2017/5/15.
 * xx
 */
public class RecruitSupplyReposTest extends JcDataRootTest {

    @Autowired
    private RecruitSupplyRepos recruitSupplyRepos;
    @Test
    public void findRecruitSuppliesByPJIDNative() throws Exception {
        List<BigInteger> temp =
        recruitSupplyRepos.findRecruitSuppliesByPJIDNative(BigInteger.valueOf(3));
        System.out.println(temp.size());
    }

}