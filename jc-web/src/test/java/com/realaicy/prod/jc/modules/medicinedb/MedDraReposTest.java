package com.realaicy.prod.jc.modules.medicinedb;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by realaicy on 2017/3/1.
 * xxx
 */
public class MedDraReposTest extends JcDataRootTest {

    @Autowired
    private MedDraRepos medDraRepos;
    @Test
    public void findByUsername() throws Exception {
        MedDra medDra =medDraRepos.findOne(BigInteger.valueOf(100L));
        assertThat(medDra.getName()).isEqualTo("多发性骨骺发育不良");
    }


    @Test
    public void findByLevel() throws Exception {
        List<MedDra> medDraList =medDraRepos.findByLevel("0");
        assertThat(medDraList.size()).isEqualTo(26);
    }

}