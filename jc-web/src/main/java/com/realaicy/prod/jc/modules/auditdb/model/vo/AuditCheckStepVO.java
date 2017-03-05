package com.realaicy.prod.jc.modules.auditdb.model.vo;


import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class AuditCheckStepVO extends BaseVO<BigInteger> {

    /**
     * 步骤所属

     */
    private BigInteger checkitemID;
    /**
     * 步骤序号
     */
    private Integer stepNo;
    /**
     * 步骤名称
     */
    private String name;
    /**
     * 步骤具体描述
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
