package com.realaicy.prod.jc.common.event;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public interface ApproveEvent<ID, T> {

    ID getApproveEntityID();

    String getApproveType();

}
