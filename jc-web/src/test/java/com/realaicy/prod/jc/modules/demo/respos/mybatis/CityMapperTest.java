package com.realaicy.prod.jc.modules.demo.respos.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by realaicy on 2017/2/13.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void findByState() throws Exception {

        /*System.out.println(cityMapper.findByState("CA").getName());
        System.out.println(cityMapper.selectAll().size());*/
    }

}