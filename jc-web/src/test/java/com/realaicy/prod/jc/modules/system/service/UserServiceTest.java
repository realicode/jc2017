package com.realaicy.prod.jc.modules.system.service;

import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * Created by realaicy on 2017/3/6.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    public static Specification<User> usersByRoleID(final BigInteger roleID) {
        return new Specification<User>() {

            @Override
            public Predicate toPredicate(final Root<User> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                final Subquery<BigInteger> roleSubQuery = query.subquery(BigInteger.class);
                final Root<Role> roleRoot = roleSubQuery.from(Role.class);
                final Join<Role, User> roleUserJoin = roleRoot.join("users");
                roleSubQuery.select(roleUserJoin.<BigInteger> get("id"));
                roleSubQuery.where(cb.equal(roleRoot.<BigInteger>get("id"), roleID));
                return cb.in(userRoot.get("id")).value(roleSubQuery);
            }
        };
    }

    public static Specification<User> usersByRoleName(final String roleName) {
        return (userRoot, query, cb) -> {

            Subquery<Role> rolesubQuery = query.subquery(Role.class);
            Root<Role> roleRoot = rolesubQuery.from(Role.class);
            Expression<Collection<User>> roleUsers = roleRoot.get("users");
            rolesubQuery.select(roleRoot);
            rolesubQuery.where(cb.like(roleRoot.get("name"),roleName),cb.isMember(userRoot,roleUsers));
            return cb.exists(rolesubQuery);

        };
    }

    public static Specification<User> usersByRoleName2(final String roleName) {
        return (userRoot, query, cb) -> {

            query.distinct(true);
            Root<Role> roleRoot = query.from(Role.class);
            Expression<Collection<User>> roleUsers = roleRoot.get("users");
            return cb.and(cb.like(roleRoot.get("name"),roleName),cb.isMember(userRoot,roleUsers));
        };
    }

    public static Specification<User> usersByOrgName(String orgName) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                return cb.like(userRoot.get("org").get("name"),orgName);

            }
        };
    }


    @Autowired
    UserService userService;

    @Test
    public void findByAd() throws Exception {
        final BaseSpecificationsBuilder<User> builder = new BaseSpecificationsBuilder<>();
        builder.with("deleteFlag", ":", false, "", "");
//        builder.with("nickname", ":", "1", "*", "*");

        Specification<User> temp1 = builder.build();

        Specification<User> temp2 = builder.build();


        final Specification<User> spec1 = usersByOrgName("虚拟机构");
        temp1 = Specifications.where(temp1).and(usersByOrgName("%拟%"));
        temp2 = Specifications.where(temp2).and(usersByRoleName("%管理%"));

        //final Specification<User> spec2 = usersByOrgName("虚拟机构");

        final Specification<User> spec3 = Specifications.where(temp2).and(usersByRoleID(BigInteger.valueOf(6L)));

        final Specification<User> spec4 = Specifications.where(temp2).and(usersByRoleName2("%管理%"));


//        List<User> poList = userService.findAll(temp1);
        List<User> poList = userService.findAll(spec4);

        System.out.println(poList.size());


//        assertThat(userRepos.findByUsername("realaicy").getOrg().getName()).isEqualTo("虚拟机构");
    }

}