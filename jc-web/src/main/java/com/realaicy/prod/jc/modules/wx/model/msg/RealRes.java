package com.realaicy.prod.jc.modules.wx.model.msg;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 *
 * xx
 */
public class RealRes implements Serializable {

    private String invalidparty;
    private String errmsg;

    @Override
    public String toString() {
        return "RealRes{"
                +
                "invalidparty='"
                + invalidparty + '\''
                +
                ", errmsg='" + errmsg + '\''
                +
                ", errcode="
                + errcode
                +
                ", invaliduser='"
                + invaliduser + '\''
                +
                ", invalidtag='"
                + invalidtag + '\''
                + '}';
    }

    private Integer errcode;

    public String getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String invalidparty) {
        this.invalidparty = invalidparty;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String invaliduser) {
        this.invaliduser = invaliduser;
    }

    public String getInvalidtag() {
        return invalidtag;
    }

    public void setInvalidtag(String invalidtag) {
        this.invalidtag = invalidtag;
    }

    private String invaliduser;
    private String invalidtag;


}
