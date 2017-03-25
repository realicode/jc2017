package com.realaicy.prod.jc.common.event;

import com.realaicy.prod.jc.modules.pj.model.Appliance;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class ApplianceConfirmDenyEvent extends JCBaseEvent implements ConfirmEvent<BigInteger, Appliance> {

    private BigInteger id;
    private String confirmType;

    public ApplianceConfirmDenyEvent(BigInteger id) {
        this.id = id;
    }

    public String getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(String confirmType) {
        this.confirmType = confirmType;
    }

    @Override
    public BigInteger getConfirmedEntityID() {
        return id;
    }
}
