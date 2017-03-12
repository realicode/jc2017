package com.realaicy.prod.jc.modules.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by realaicy on 2017/3/12.
 * xxs
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserSecServiceTest {

    @Autowired
    private UserSecService userSecService;
    @Test
    public void findByUsername() throws Exception {
        userSecService.findByUsername("stest1");
    }

}