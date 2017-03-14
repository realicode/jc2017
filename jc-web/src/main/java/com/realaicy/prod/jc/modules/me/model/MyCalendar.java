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
@Table(name = "jc_me_calendar")
public class MyCalendar extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "CAL_NAME")
    private String name;
    /**
     * 标题
     */
    @Column(name = "CAL_TITLE")
    private String title;
    /**
     * 副标题
     */
    @Column(name = "CAL_SUBTITLE")
    private String subtitle;

    /**
     * 代办事项类型
     */
    @Column(name = "CAL_TYPE")
    private String calendarType;

    /**
     * 所属用户
     */
    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * 紧急程度
     */
    @Column(name = "CAL_LEVEL")
    private Short level;

    /**
     * 开始日期
     */
    @Column(name = "STARTDATE")
    private Date startDate;

    /**
     * 开始日期
     */
    @Column(name = "ENDDATE")
    private Date endDate;

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

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
