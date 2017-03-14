package com.realaicy.prod.jc.modules.wx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 *
 */
public class RealTK implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private String expires;

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
