package com.realaicy.prod.jc.modules.pj.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.math.BigInteger;

/**
 * 招募提供者VO
 */
public class ExpertSupplyVO extends BaseVO<BigInteger> {

    private BigInteger id;
    /**
     * 招募工作的类型
     */
    private String workType;
    private String comment;
    private BigInteger projectFacadeID;
    private RecruitSupplyVO recruitSupplyVO1;
    private RecruitSupplyVO recruitSupplyVO2;
    private RecruitSupplyVO recruitSupplyVO3;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigInteger getProjectFacadeID() {
        return projectFacadeID;
    }

    public void setProjectFacadeID(BigInteger projectFacadeID) {
        this.projectFacadeID = projectFacadeID;
    }

    public RecruitSupplyVO getRecruitSupplyVO1() {
        return recruitSupplyVO1;
    }

    public void setRecruitSupplyVO1(RecruitSupplyVO recruitSupplyVO1) {
        this.recruitSupplyVO1 = recruitSupplyVO1;
    }

    public RecruitSupplyVO getRecruitSupplyVO2() {
        return recruitSupplyVO2;
    }

    public void setRecruitSupplyVO2(RecruitSupplyVO recruitSupplyVO2) {
        this.recruitSupplyVO2 = recruitSupplyVO2;
    }

    public RecruitSupplyVO getRecruitSupplyVO3() {
        return recruitSupplyVO3;
    }

    public void setRecruitSupplyVO3(RecruitSupplyVO recruitSupplyVO3) {
        this.recruitSupplyVO3 = recruitSupplyVO3;
    }
}
