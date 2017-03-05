package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by realaicy on 2017/2/26.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserSecReposTest {
    @Autowired
    private UserSecRepos userSecRepos;
    @Test
    public void findByUsername() throws Exception {
        UserSec userSec =userSecRepos.findByUsername("realaicy");
        assertThat(userSec.getNickname()).isEqualTo("刘旭东");
    }

    @Test
    public void save() throws Exception {

        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2017-01-01");

        UserSec userSecTBSave = new UserSec();
        userSecTBSave.setAccountNonExpired(true);
        userSecTBSave.setAccountNonLocked(true);
        userSecTBSave.setCredentialsNonExpired(true);
        userSecTBSave.setEnabled(true);
        userSecTBSave.setNickname("realtestusernickname");
        userSecTBSave.setPassword("password");
        userSecTBSave.setUsername("realtestusername");
        userSecTBSave.setCreaterID(BigInteger.ONE);
        userSecTBSave.setCreateTime(date);
        userSecTBSave.setUpdaterID(BigInteger.ONE);
        userSecTBSave.setUpdateTime(date);
        userSecTBSave.setCustomCode("9999");
        userSecTBSave.setStatus(Short.valueOf("1"));
        userSecTBSave.setDeleteFlag(false);
        userSecTBSave.setId(BigInteger.valueOf(9999L));

        userSecRepos.save(userSecTBSave);
    }

    @Test
    public void findBySpec() throws Exception {
        final BaseSpecificationsBuilder<UserSec> builder = new BaseSpecificationsBuilder<>();
        builder.with("username", ":", "realaicy", "", "*");
        final Specification<UserSec> spec = builder.build();

        Long childSize = userSecRepos.count(spec);
        System.out.println("realaicy's count(): " + childSize);
    }


}