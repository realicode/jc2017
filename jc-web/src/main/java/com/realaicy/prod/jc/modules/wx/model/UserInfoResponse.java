package com.realaicy.prod.jc.modules.wx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 *
 */
public class UserInfoResponse implements Serializable {

    @JsonProperty("UserId")
    private String userID;
    @JsonProperty("DeviceId")
    private String deviceId;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
