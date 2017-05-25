package com.realaicy.prod.jc.modules.pj.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 检查模块实例VO
 */
public class PreInspectionCheckRuntimeVO extends BaseVO<BigInteger> {
    private BigInteger id;
    private BigInteger preInspectionID;
    private BigInteger checkmoduleID;
    /**
     * 检查项目名称
     */
    private String name;
    private int score;
    private String itemgrade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date finishDate;
    private String checkername;
    private BigInteger checker;
    private String checkRemark;
    private Short status;
    public PreInspectionCheckRuntimeVO() {
    }

    public PreInspectionCheckRuntimeVO(BigInteger checkmoduleID, String name, Date deadline) {
        this.checkmoduleID = checkmoduleID;
        this.name = name;
        this.deadline = deadline;
    }

    public String getItemgrade() {
        return itemgrade;
    }

    public void setItemgrade(String itemgrade) {
        this.itemgrade = itemgrade;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public BigInteger getChecker() {
        return checker;
    }

    public void setChecker(BigInteger checker) {
        this.checker = checker;
    }

    public BigInteger getCheckmoduleID() {
        return checkmoduleID;
    }

    public void setCheckmoduleID(BigInteger checkmoduleID) {
        this.checkmoduleID = checkmoduleID;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getPreInspectionID() {
        return preInspectionID;
    }

    public void setPreInspectionID(BigInteger preInspectionID) {
        this.preInspectionID = preInspectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getCheckername() {
        return checkername;
    }

    public void setCheckername(String checkername) {
        this.checkername = checkername;
    }
}
