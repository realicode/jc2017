package com.realaicy.prod.jc.modules.system.service;

import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.system.model.Org;
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
import java.util.List;

/**
 * Created by realaicy on 2017/3/6.
 * xxx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    public static Specification<User> usersByOrgID(final Long orgID) {
        return new Specification<User>() {

            @Override
            public Predicate toPredicate(final Root<User> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                final Subquery<Long> orgQuery = query.subquery(Long.class);
                final Root<Org> org = orgQuery.from(Org.class);
                final Join<Org, User> users = org.join("userList");
                orgQuery.select(users.<Long> get("id"));
                orgQuery.where(cb.equal(org.<Long> get("id"), orgID));

                return cb.in(userRoot.get("id")).value(orgQuery);
            }
        };
    }

    public static Specification<User> usersByOrgName(String orgName) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                return cb.equal(userRoot.get("org").get("name"),orgName);

            }
        };
    }

    @Autowired
    UserService userService;

    @Test
    public void findByAd() throws Exception {
        final BaseSpecificationsBuilder<User> builder = new BaseSpecificationsBuilder<>();
        builder.with("deleteFlag", ":", false, "", "");
        builder.with("nickname", ":", "1", "*", "*");

        final Specification<User> temp1 = builder.build();

        final Specification<User> spec = usersByOrgID(82L);
        final Specification<User> spec1 = usersByOrgName("虚拟机构");
        final Specification<User> specFinal = Specifications.where(temp1).and(spec1);

        //final Specification<User> spec2 = usersByOrgName("虚拟机构");

        List<User> poList = userService.findAll(specFinal);
        System.out.println(poList.size());


//        assertThat(userRepos.findByUsername("realaicy").getOrg().getName()).isEqualTo("虚拟机构");
    }

}