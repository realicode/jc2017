package com.realaicy.prod.jc.modules.me.model.vo;


import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.math.BigInteger;
import java.util.Date;

/**
 * 代办VO
 */
public class MyTodoVO extends BaseVO<BigInteger> {

    /**
     * 名称
     */
    private String name;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 代办事项类型
     */
    private String todoType;

    /**
     * 紧急程度
     */
    private Short level;

    /**
     * 最后截止日期
     */
    private Date deadline;

    /**
     * 处理日期
     */
    private Date processDate;

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


}
