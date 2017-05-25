package com.realaicy.prod.jc.common.event;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by realaicy on 2017/3/15.
 * xx
 */
public class PreRecruitPubEvent extends JCBaseEvent {

    private BigInteger checkitemruntimeid;
    private BigInteger checkerid;
    private BigInteger pjid;
    private Date deadline;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigInteger getPjid() {
        return pjid;
    }

    public void setPjid(BigInteger pjid) {
        this.pjid = pjid;
    }

    public BigInteger getCheckerid() {
        return checkerid;
    }

    public void setCheckerid(BigInteger checkerid) {
        this.checkerid = checkerid;
    }

    public BigInteger getCheckitemruntimeid() {

        return checkitemruntimeid;
    }

    public void setCheckitemruntimeid(BigInteger checkitemruntimeid) {
        this.checkitemruntimeid = checkitemruntimeid;
    }
}
