package com.realaicy.prod.jc.modules.system.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * VO
 */
public class EventActionVO extends BaseVO<BigInteger> {

    /**
     * 名称
     */
    @NotEmpty
    private String name;
    /**
     * 动作
     */
    private String eventAction;
    /**
     * 订阅人ID
     */
    private String subscriberID;
    /**
     * 订阅角色ID
     */
    private String subscriberRoleID;
    /**
     * 订阅组ID
     */
    private String subscriberGroupID;
    /**
     * 消息模版ID
     */
    private String msgTemID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventAction() {
        return eventAction;
    }

    public void setEventAction(String eventAction) {
        this.eventAction = eventAction;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getSubscriberRoleID() {
        return subscriberRoleID;
    }

    public void setSubscriberRoleID(String subscriberRoleID) {
        this.subscriberRoleID = subscriberRoleID;
    }

    public String getSubscriberGroupID() {
        return subscriberGroupID;
    }

    public void setSubscriberGroupID(String subscriberGroupID) {
        this.subscriberGroupID = subscriberGroupID;
    }

    public String getMsgTemID() {
        return msgTemID;
    }

    public void setMsgTemID(String msgTemID) {
        this.msgTemID = msgTemID;
    }
}
