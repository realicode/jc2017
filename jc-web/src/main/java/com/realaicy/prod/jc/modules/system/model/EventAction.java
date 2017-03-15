package com.realaicy.prod.jc.modules.system.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_event_action")
public class EventAction extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "EVENT_KEY")
    @NotEmpty
    private String name;
    /**
     * 动作
     */
    @Column(name = "ENENT_ACTION")
    private String eventAction;
    /**
     * 订阅人ID
     */
    @Column(name = "EVENT_SUBSCRIBER_ID")
    private BigInteger subscriberID;
    /**
     * 订阅角色ID
     */
    @Column(name = "EVENT_SUBSCRIBER_ROLEID")
    private BigInteger subscriberRoleID;
    /**
     * 订阅组ID
     */
    @Column(name = "EVENT_SUBSCRIBER_GROUPID")
    private BigInteger subscriberGroupID;
    /**
     * 消息模版ID
     */
    @Column(name = "EVENT_ACTION_MSG_TEMID")
    private BigInteger msgTemID;

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

    public BigInteger getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(BigInteger subscriberID) {
        this.subscriberID = subscriberID;
    }

    public BigInteger getSubscriberRoleID() {
        return subscriberRoleID;
    }

    public void setSubscriberRoleID(BigInteger subscriberRoleID) {
        this.subscriberRoleID = subscriberRoleID;
    }

    public BigInteger getSubscriberGroupID() {
        return subscriberGroupID;
    }

    public void setSubscriberGroupID(BigInteger subscriberGroupID) {
        this.subscriberGroupID = subscriberGroupID;
    }

    public BigInteger getMsgTemID() {
        return msgTemID;
    }

    public void setMsgTemID(BigInteger msgTemID) {
        this.msgTemID = msgTemID;
    }
}
