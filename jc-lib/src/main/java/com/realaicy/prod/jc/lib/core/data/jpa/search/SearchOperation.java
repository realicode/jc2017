package com.realaicy.prod.jc.lib.core.data.jpa.search;

/**
 * Created by realaicy on 16/8/16.
 * 查询操作符
 */
public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, REALAICY_IN, STARTS_WITH, ENDS_WITH, CONTAINS;

    public static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", "<", "~", "$"};

    public static SearchOperation getSimpleOperation(char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            case '$':
                return REALAICY_IN;
            default:
                return null;
        }
    }
}
