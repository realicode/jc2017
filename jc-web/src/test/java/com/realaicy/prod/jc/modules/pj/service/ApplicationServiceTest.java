package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.modules.pj.model.Appliance;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/13.
 * xxx
 */
public class ApplicationServiceTest {
    public static Specification<Appliance> applicaitonByUserID(BigInteger userID) {
        return new Specification<Appliance>() {
            @Override
            public Predicate toPredicate(final Root<Appliance> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                return cb.equal(userRoot.get("user").get("id"), userID);

            }
        };
    }

}