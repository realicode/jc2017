package com.realaicy.prod.jc.modules.me.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.UserInfo;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 我的消息
 */
@Entity
@Table(name = "jc_me_msg")
public class MyMessage extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "MSG_NAME")
    private String name;
    /**
     * 标题
     */
    @Column(name = "MSG_TITLE")
    private String title;
    /**
     * 副标题
     */
    @Column(name = "MSG_SUBTITLE")
    private String subtitle;

    /**
     * 消息类型
     */
    @Column(name = "MSG_TYPE")
    private String msgType;

    /**
     * 紧急程度
     */
    @Column(name = "MSG_LEVEL")
    private Short level;

    /**
     * 处理日期
     */
    @Column(name = "DODATE")
    private Date processDate;

    /**
     * 所属用户
     */
    @ManyToOne()
    @JoinColumn(name = "DIST_USER_ID")
    private UserInfo distUserInfo;
    /**
     * 发送者
     */
    @ManyToOne()
    @JoinColumn(name = "SRC_USER_ID")
    private UserInfo srcUserInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public UserInfo getDistUserInfo() {
        return distUserInfo;
    }

    public void setDistUserInfo(UserInfo distUserInfo) {
        this.distUserInfo = distUserInfo;
    }

    public UserInfo getSrcUserInfo() {
        return srcUserInfo;
    }

    public void setSrcUserInfo(UserInfo srcUserInfo) {
        this.srcUserInfo = srcUserInfo;
    }

}
