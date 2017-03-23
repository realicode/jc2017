package com.realaicy.prod.jc.modules.me.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.User;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 代办
 */
@Entity
@Table(name = "jc_m_me_work")
public class MyWork extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "WORKNAME")
    private String name;
    /**
     * 标题
     */
    @Column(name = "MAINTITLE")
    private String mainTitle;
    /**
     * 副标题
     */
    @Column(name = "SUBTITLE")
    private String subTitle;

    /**
     * 工作类型
     */
    @Column(name = "WORKTYPE")
    private String workType;

    /**
     * 紧急程度
     */
    @Column(name = "WORKLEVEL")
    private Short workLevel;
    /**
     * 处理URI
     */
    @Column(name = "WORKURI")
    private String workUri;
    /**
     * 查看URI
     */
    @Column(name = "VIEWURI")
    private String viewUri;
    /**
     * 最后截止日期
     */
    @Column(name = "DEADLINE")
    private Date deadline;
    /**
     * 处理日期
     */
    @Column(name = "PROCESSDATE")
    private Date processDate;
    /**
     * 所属用户
     */
    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    public String getViewUri() {
        return viewUri;
    }

    public void setViewUri(String viewUri) {
        this.viewUri = viewUri;
    }

    public String getWorkUri() {
        return workUri;
    }

    public void setWorkUri(String workUri) {
        this.workUri = workUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Short getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(Short workLevel) {
        this.workLevel = workLevel;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
