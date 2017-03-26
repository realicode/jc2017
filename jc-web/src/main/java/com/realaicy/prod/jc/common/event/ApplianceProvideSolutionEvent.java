package com.realaicy.prod.jc.common.event;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class ApplianceProvideSolutionEvent extends JCBaseEvent {

    private BigInteger applyid;
    private String buildType;

    public BigInteger getApplyid() {
        return applyid;
    }

    public void setApplyid(BigInteger applyid) {
        this.applyid = applyid;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }


}
