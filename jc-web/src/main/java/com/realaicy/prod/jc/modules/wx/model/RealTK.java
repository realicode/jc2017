package com.realaicy.prod.jc.modules.wx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 * xxx
 */
public class RealTK implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private String expires;

    private String errcode = "0";
    private String errmsg = "";

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "RealTK{"
                +
                "accessToken='"
                + accessToken + '\''
                +
                ", expires='" + expires + '\''
                + '}';
    }
}
