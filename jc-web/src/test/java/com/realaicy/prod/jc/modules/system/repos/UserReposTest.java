package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.system.model.User;
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
public class UserReposTest {
    @Autowired
    private UserRepos userRepos;
    @Test
    public void findByUsername() throws Exception {
        User user = userRepos.findByUsername("realaicy");
        assertThat(user.getNickname()).isEqualTo("刘旭东");
    }

    @Test
    public void save() throws Exception {

        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2017-01-01");

        User userTBSave = new User();
        userTBSave.setAccountNonExpired(true);
        userTBSave.setAccountNonLocked(true);
        userTBSave.setCredentialsNonExpired(true);
        userTBSave.setEnabled(true);
        userTBSave.setNickname("realtestusernickname");
        userTBSave.setPassword("password");
        userTBSave.setUsername("realtestusername");
        userTBSave.setCreaterID(BigInteger.ONE);
        userTBSave.setCreateTime(date);
        userTBSave.setUpdaterID(BigInteger.ONE);
        userTBSave.setUpdateTime(date);
        userTBSave.setCustomCode("9999");
        userTBSave.setStatus(Short.valueOf("1"));
        userTBSave.setDeleteFlag(false);
        userTBSave.setId(BigInteger.valueOf(9999L));

        userRepos.save(userTBSave);
    }

    @Test
    public void findBySpec() throws Exception {
        final BaseSpecificationsBuilder<User> builder = new BaseSpecificationsBuilder<>();
        builder.with("username", ":", "realaicy", "", "*");
        final Specification<User> spec = builder.build();

        Long childSize = userRepos.count(spec);
        System.out.println("realaicy's count(): " + childSize);
    }


}