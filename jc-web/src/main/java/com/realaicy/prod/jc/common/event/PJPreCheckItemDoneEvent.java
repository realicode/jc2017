package com.realaicy.prod.jc.common.event;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class PJPreCheckItemDoneEvent extends JCBaseEvent {

    private BigInteger checkitemruntimeid;

    public BigInteger getCheckitemruntimeid() {
        return checkitemruntimeid;
    }

    public void setCheckitemruntimeid(BigInteger checkitemruntimeid) {
        this.checkitemruntimeid = checkitemruntimeid;
    }
}
