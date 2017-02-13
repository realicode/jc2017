package com.realaicy.prod.jc.lib.core.data.jpa.search;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;


/**
 * Created by realaicy on 16/8/16.
 * x
 *
 * @param <T> the type parameter
 */
public class BaseSpecification<T> implements Specification<T> {

    private SpecSearchCriteria criteria;

    /**
     * Instantiates a new Base specification.
     *
     * @param criteria the criteria
     */
    public BaseSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    /**
     *
     * @param root xx
     * @param query xx
     * @param builder xxx
     * @return xxx
     */
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case REALAICY_IN:
                return root.get(criteria.getKey()).in((List<BigInteger>) criteria.getValue());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }

}
