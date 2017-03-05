package com.realaicy.prod.jc.modules.auditdb.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 稽查检查项-具体步骤类
 */
@Entity
@Table(name = "jc_m_audit_checkstep")
public class AuditCheckStep extends CommonDeletableEntity<BigInteger> {

    /**
     * 步骤序号
     */
    @Column(name = "CHECKITEMID")
    private BigInteger checkitemID;
    /**
     * 步骤序号
     */
    @Column(name = "STEPNO")
    private Integer stepNo;
    /**
     * 步骤名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 步骤具体描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    public BigInteger getCheckitemID() {
        return checkitemID;
    }

    public void setCheckitemID(BigInteger checkitemID) {
        this.checkitemID = checkitemID;
    }

    public Integer getStepNo() {
        return stepNo;
    }

    public void setStepNo(Integer stepNo) {
        this.stepNo = stepNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
