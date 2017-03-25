package com.realaicy.prod.jc.common.event;

import com.realaicy.prod.jc.modules.pj.model.Appliance;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class ApplianceApproveEvent extends JCBaseEvent implements ApproveEvent<BigInteger, Appliance> {

    private BigInteger id;

    private String approveType;

    public ApplianceApproveEvent(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getApproveEntityID() {
        return id;
    }

    @Override
    public String getApproveType() {
        return approveType;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType;
    }
}

