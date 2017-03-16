package com.realaicy.prod.jc.modules.me.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MyTodoReposTest {
    @Autowired
    MyTodoRepos myTodoRepos;

    @Test
    public void testfind(){

    }

}