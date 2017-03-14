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
@Table(name = "jc_me_todo")
public class MyWork extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "TODONAME")
    private String name;
    /**
     * 标题
     */
    @Column(name = "ROLESTATUS")
    private String title;
    /**
     * 副标题
     */
    @Column(name = "ROLETYPE")
    private String subtitle;

    /**
     * 代办事项类型
     */
    @Column(name = "TODOTYPE")
    private String todoType;

    /**
     * 紧急程度
     */
    @Column(name = "TODOLEVEL")
    private Short level;

    /**
     * 最后截止日期
     */
    @Column(name = "DEADLINE")
    private Date deadline;

    /**
     * 处理日期
     */
    @Column(name = "DODATE")
    private Date processDate;
    /**
     * 所属用户
     */
    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public String getTodoType() {
        return todoType;
    }

    public void setTodoType(String todoType) {
        this.todoType = todoType;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
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
