package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.pj.model.Application;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by realaicy on 2017/3/13.
 * xxx
 */
public class ApplicationServiceTest {
    public static Specification<Application> applicaitonByUserID(BigInteger userID) {
        return new Specification<Application>() {
            @Override
            public Predicate toPredicate(final Root<Application> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                return cb.equal(userRoot.get("user").get("id"), userID);

            }
        };
    }
    @Test
    public void aTest(){
        final BaseSpecificationsBuilder<Application> builder = new BaseSpecificationsBuilder<>();
        builder.with("deleteFlag", ":", false, "", "");

        Specification<Application> temp1 = builder.build();
        BigInteger integer = SpringSecurityUtil.getCurrentRealUserDetails().getId();

        final Specification<Application> spec1 = applicaitonByUserID(integer);
        temp1 = Specifications.where(temp1).and(spec1);
        System.out.println();
    }

}