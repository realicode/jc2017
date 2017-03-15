package com.realaicy.prod.jc.lib.core.data.jpa.search;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;


public final class BaseSpecificationsBuilder<T> {

    private final List<SpecSearchCriteria> params;

    public BaseSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    // API

    public  BaseSpecificationsBuilder with(final String key, final String operation,
                                                final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            /*if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix.contains("*");
                final boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }*/
            params.add(new SpecSearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        final List<Specification<T>> specs = new ArrayList<>();
        for (final SpecSearchCriteria param : params) {
            specs.add(new BaseSpecification<T>(param));
        }

        Specification<T> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }

}
