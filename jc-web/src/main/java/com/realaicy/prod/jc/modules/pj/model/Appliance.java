package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_pj_application")
public class Appliance extends CommonDeletableEntity<BigInteger> {


    /**
     * 申请名称
     */
    @Column(name = "APPLYNAME")
    @NotEmpty
    private String name;

    /**
     * 申办方机构名称
     */
    @Column(name = "SPONSOR_ORGNAME")
    @NotEmpty
    private String orgName;

    /**
     * 方案摘要URI
     */
    @Column(name = "TRIALABSTRACTURI")
    @NotEmpty
    private String trialAbstractURI;

    /**
     * 方案摘要Name
     */
    @Column(name = "TRIALABSTRACTNAME")
    @NotEmpty
    private String trialAbstractName;

    /**
     * 带稽查试验机构名称
     */
    @Column(name = "TRIALCENTERS")
    private String trialCenterNames;

    /**
     * 申请的简要描述
     */
    @NotEmpty
    @Column(name = "APPLY_DESCRIBE")
    private String applyDescribe;

    /**
     * 确认人
     */
    @ManyToOne()
    @JoinColumn(name = "CONFIRM_USERID")
    private User confirmor;

    /**
     * "确认人"评论
     */
    @Column(name = "CONFIRM_REMARK")
    private String confirmRemark;

    /**
     * 审批人
     */
    @ManyToOne()
    @JoinColumn(name = "APPROVE_USERID")
    private User approver;

    /**
     * "审批人"评论
     */
    @Column(name = "APPROVE_REMARK")
    private String approveRemark;

    /**
     * 报价
     */
    @Column(name = "QUOTATION")
    private Integer quotation;

    /**
     * 合同URI
     */
    @Column(name = "CONTRACTURI")
    private String contractURI;

    /**
     * 研究方案URI
     */
    @Column(name = "TRIALURI")
    private String trialURI;

    /**
     * 临时测试
     */
    @Column(name = "CONTRACTTMP1")
    private String contracttmp1;

    /**
     * 临时测试2
     */
    @Column(name = "CONTRACTTMP2")
    private String contracttmp2;
    /**
     * 最终确认意见
     */
    @Column(name = "FINALREMARK")
    private String finalRemark;

    @ManyToOne()
    @JoinColumn(name = "APPLICANT_ID")
    private User user;

    public String getFinalRemark() {
        return finalRemark;
    }

    public void setFinalRemark(String finalRemark) {
        this.finalRemark = finalRemark;
    }

    public String getContracttmp2() {
        return contracttmp2;
    }

    public void setContracttmp2(String contracttmp2) {
        this.contracttmp2 = contracttmp2;
    }

    public String getContracttmp1() {
        return contracttmp1;
    }

    public void setContracttmp1(String contracttmp1) {
        this.contracttmp1 = contracttmp1;
    }

    public String getContractURI() {
        return contractURI;
    }

    public void setContractURI(String contractURI) {
        this.contractURI = contractURI;
    }

    public String getTrialURI() {
        return trialURI;
    }

    public void setTrialURI(String trialURI) {
        this.trialURI = trialURI;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public User getConfirmor() {
        return confirmor;
    }

    public void setConfirmor(User confirmor) {
        this.confirmor = confirmor;
    }

    public String getConfirmRemark() {
        return confirmRemark;
    }

    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }

    public Integer getQuotation() {
        return quotation;
    }

    public void setQuotation(Integer quotation) {
        this.quotation = quotation;
    }

    public String getApplyDescribe() {
        return applyDescribe;
    }

    public void setApplyDescribe(String applyDescribe) {
        this.applyDescribe = applyDescribe;
    }

    public String getTrialAbstractName() {
        return trialAbstractName;
    }

    public void setTrialAbstractName(String trialAbstractName) {
        this.trialAbstractName = trialAbstractName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTrialAbstractURI() {
        return trialAbstractURI;
    }

    public void setTrialAbstractURI(String trialAbstractURI) {
        this.trialAbstractURI = trialAbstractURI;
    }

    public String getTrialCenterNames() {
        return trialCenterNames;
    }

    public void setTrialCenterNames(String trialCenterNames) {
        this.trialCenterNames = trialCenterNames;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
