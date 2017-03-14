package com.realaicy.prod.jc.modules.wx.model.msg;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 *
 */
public class WxMsg implements Serializable {

    private String touser;
    private String msgtype;
    private Integer agentid;
    private RealMsgContent text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public RealMsgContent getText() {
        return text;
    }

    public void setText(RealMsgContent text) {
        this.text = text;
    }
}
