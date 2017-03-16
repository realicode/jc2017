package com.realaicy.prod.jc.modules.me.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 我的工作VO
 */
public class MyWorkVO extends BaseVO<BigInteger> {

    /**
     * ID
     */
    private BigInteger id;
    /**
     * 名称
     */
    private String name;
    /**
     * 标题
     */
    private String mainTitle;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 工作类型
     */
    private String workType;
    /**
     * 紧急程度
     */
    private Short workLevel;
    /**
     * 最后截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "Asia/Shanghai")
    private Date deadline;
    /**
     * 处理日期
     */
    private Date processDate;
    /**
     * 工作状态
     */
    private Short workStatus;

    public MyWorkVO() {
    }

    public MyWorkVO(MyWork po) {
        this.id = po.getId();
        this.name = po.getName();
        this.mainTitle = po.getMainTitle();
        this.subTitle = po.getSubTitle();
        this.deadline = po.getDeadline();
        this.workStatus = po.getStatus();
        this.workLevel = po.getWorkLevel();

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Short getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Short workStatus) {
        this.workStatus = workStatus;
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


}
