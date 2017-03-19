package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by realaicy on 2017/3/19.
 * xxx
 */
public class UserSecReposTest  extends JcDataRootTest {

    @Autowired
    private UserSecRepos userSecRepos;

    @Test
    public void findByUsername() throws Exception {
        System.out.println("________________________________________________________");
        userSecRepos.findByUsername("realaicy");
        System.out.println("________________________________________________________");
        userSecRepos.findByUsername("realaicy");
        userSecRepos.findByUsername("realaicy");
        userSecRepos.findByUsername("realaicy");


    }

}