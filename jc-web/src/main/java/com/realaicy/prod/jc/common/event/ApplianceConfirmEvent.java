package com.realaicy.prod.jc.common.event;

import com.realaicy.prod.jc.modules.pj.model.Appliance;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class ApplianceConfirmEvent extends JCBaseEvent implements ConfirmEvent<BigInteger, Appliance> {

    private BigInteger id;

    public ApplianceConfirmEvent(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getConfirmedEntityID() {
        return id;
    }
}
