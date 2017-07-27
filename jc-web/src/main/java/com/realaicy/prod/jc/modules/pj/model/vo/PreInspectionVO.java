package com.realaicy.prod.jc.modules.pj.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 项目门面实体VO
 */
public class PreInspectionVO extends BaseVO<BigInteger> {

    private BigInteger id;
    private BigInteger pjID;
    private BigInteger preleader;
    private String precheckers;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date preSDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date preFDate;
    private String checkStr;
    private int score;
    private short status;
    private String projectName;
    private BigInteger checkModuleID;
    private String checkModuleName;
    private List<PreInspectionCheckItemRuntimeVO> checkitems;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getCheckModuleName() {
        return checkModuleName;
    }

    public void setCheckModuleName(String checkModuleName) {
        this.checkModuleName = checkModuleName;
    }

    public BigInteger getCheckModuleID() {
        return checkModuleID;
    }

    public void setCheckModuleID(BigInteger checkModuleID) {
        this.checkModuleID = checkModuleID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCheckStr() {
        return checkStr;
    }

    public void setCheckStr(String checkStr) {
        this.checkStr = checkStr;
    }

    public BigInteger getPjID() {
        return pjID;
    }

    public void setPjID(BigInteger pjID) {
        this.pjID = pjID;
    }

    public List<PreInspectionCheckItemRuntimeVO> getCheckitems() {
        return checkitems;
    }

    public void setCheckitems(List<PreInspectionCheckItemRuntimeVO> checkitems) {
        this.checkitems = checkitems;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BigInteger getPreleader() {
        return preleader;
    }

    public void setPreleader(BigInteger preleader) {
        this.preleader = preleader;
    }

    public String getPrecheckers() {
        return precheckers;
    }

    public void setPrecheckers(String precheckers) {
        this.precheckers = precheckers;
    }

    public Date getPreSDate() {
        return preSDate;
    }

    public void setPreSDate(Date preSDate) {
        this.preSDate = preSDate;
    }

    public Date getPreFDate() {
        return preFDate;
    }

    public void setPreFDate(Date preFDate) {
        this.preFDate = preFDate;
    }
}
