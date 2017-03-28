package com.realaicy.prod.jc.modules.pj.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 项目门面实体VO
 */
public class PreInspectionCheckItemRuntimeVO extends BaseVO<BigInteger> {
    private BigInteger id;
    private BigInteger preInspectionID;
    private BigInteger checkitemID;
    private String name;
    private int score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date finishDate;
    private String checkername;
    private BigInteger checker;



    public PreInspectionCheckItemRuntimeVO() {
    }
    public PreInspectionCheckItemRuntimeVO(BigInteger checkitemID, String name, Date deadline) {
        this.checkitemID = checkitemID;
        this.name = name;
        this.deadline = deadline;
    }

    public BigInteger getCheckitemID() {
        return checkitemID;
    }

    public void setCheckitemID(BigInteger checkitemID) {
        this.checkitemID = checkitemID;
    }

    public BigInteger getChecker() {
        return checker;
    }

    public void setChecker(BigInteger checker) {
        this.checker = checker;
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
