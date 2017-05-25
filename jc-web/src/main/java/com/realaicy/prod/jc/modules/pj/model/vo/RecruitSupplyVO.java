package com.realaicy.prod.jc.modules.pj.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 招募提供者VO
 */
public class RecruitSupplyVO extends BaseVO<BigInteger> {

    private BigInteger id;
    /**
     * 招募工作的类型
     */
    @NotEmpty
    private String workType;
    private String comment;
    private BigInteger projectFacadeID;
    private String projectFacadeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date deadLine;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date estStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date estEndDate;

    public RecruitSupplyVO() {
    }

    public RecruitSupplyVO(RecruitSupply recruitSupply) {
        this.id = recruitSupply.getId();
        this.workType = recruitSupply.getWorkType();
        this.projectFacadeID = recruitSupply.getProjectFacade().getId();
        this.projectFacadeName = recruitSupply.getProjectFacade().getName();
        this.estStartDate = recruitSupply.getEstStartDate();
        this.estEndDate = recruitSupply.getEstEndDate();
        this.deadLine = recruitSupply.getDeadLine();
        this.comment = recruitSupply.getComment();
    }

    public String getProjectFacadeName() {
        return projectFacadeName;
    }

    public void setProjectFacadeName(String projectFacadeName) {
        this.projectFacadeName = projectFacadeName;
    }

    public BigInteger getProjectFacadeID() {
        return projectFacadeID;
    }

    public void setProjectFacadeID(BigInteger projectFacadeID) {
        this.projectFacadeID = projectFacadeID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }


    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getEstStartDate() {
        return estStartDate;
    }

    public void setEstStartDate(Date estStartDate) {
        this.estStartDate = estStartDate;
    }

    public Date getEstEndDate() {
        return estEndDate;
    }

    public void setEstEndDate(Date estEndDate) {
        this.estEndDate = estEndDate;
    }
}
