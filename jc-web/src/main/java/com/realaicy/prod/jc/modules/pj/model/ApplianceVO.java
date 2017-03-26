package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class ApplianceVO extends BaseVO<BigInteger> {

    /**
     * ID
     */
    private BigInteger id;
    /**
     * 申请名称
     */
    @NotEmpty
    @Size(min = 5, max = 300)
    private String name;

    /**
     * 申办方机构名称
     */
    @NotEmpty
    @Size(min = 5, max = 200)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4E00-\\u9FA5]+$", message = "申请名称格式不正确")
    private String orgName;

    /**
     * 方案摘要URI
     */
    @NotEmpty
    private String trialAbstractURI;

    @NotEmpty
    @Size(min = 5, max = 200)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4E00-\\u9FA5]+$", message = "申请名称格式不正确")
    private String trialAbstractName;

    /**
     * 待稽查试验机构名称
     */
    @NotEmpty
    @Size(max = 2000)
    private String trialCenterNames;

    /**
     * 申请状态
     */
    private String applyStatus;

    /**
     * 申请的简要描述
     */
    @NotEmpty
    @Size(min = 2, max = 500)
    private String applyDescribe;

    /**
     * 申请人
     */
    private String applicantName;

    /**
     * 确认人
     */
    private String confirmorName;

    /**
     * "审批人"评论
     */
    @Size(min = 2, max = 500)
    private String confirmRemark;

    /**
     * 审批人
     */
    private String approverName;

    /**
     * "审批人"评论
     */
    @Size(min = 2, max = 500)
    private String approveRemark;

    /**
     * 报价
     */
    @Digits(integer = 10000000, fraction = 1)
    private Integer quotation;

    /**
     * 合同URI
     */
    private String contractURI;

    /**
     * 方案URI
     */
    private String trialURI;
    /**
     * 临时测试
     */
    private String contracttmp1;
    /**
     * 临时测试2
     */
    private String contracttmp2;


    /**
     * 最终确认意见
     */
    private String finalRemark;

    public ApplianceVO() {
    }

    public ApplianceVO(Appliance po) {

//        BeanUtils.copyProperties(po, this);
        this.id = po.getId();
        this.name = po.getName();
        this.orgName = po.getOrgName();
        this.trialAbstractURI = po.getTrialAbstractURI();
        this.trialAbstractName = po.getTrialAbstractName();
        this.trialCenterNames = po.getTrialCenterNames();
        this.applyStatus = String.valueOf(po.getStatus());
        this.applyDescribe = po.getApplyDescribe();
        this.confirmRemark = po.getConfirmRemark();
        this.approveRemark = po.getApproveRemark();
        this.contracttmp1 = po.getContracttmp1();
        this.contractURI = po.getContractURI();
        this.trialURI  = po.getTrialURI();
        this.contracttmp2 = po.getContracttmp2();
        this.finalRemark = po.getFinalRemark();

        this.applicantName = po.getUser().getNickname();
        if (po.getConfirmor() != null) {
            this.confirmorName = po.getConfirmor().getNickname();
        }
        if (po.getApprover() != null) {
            this.approverName = po.getApprover().getNickname();
        }

        this.quotation = po.getQuotation();

//        if (SpringSecurityUtil.hasPrivilege(Appliance.class.getSimpleName() + "-ack")) {
//            this.btnType = "2"; //有确认按钮
//        }
    }

    public String getTrialURI() {
        return trialURI;
    }

    public void setTrialURI(String trialURI) {
        this.trialURI = trialURI;
    }

    public String getContracttmp2() {
        return contracttmp2;
    }

    public void setContracttmp2(String contracttmp2) {
        this.contracttmp2 = contracttmp2;
    }

    public String getContractURI() {
        return contractURI;
    }

    public void setContractURI(String contractURI) {
        this.contractURI = contractURI;
    }

    public String getContracttmp1() {
        return contracttmp1;
    }

    public void setContracttmp1(String contracttmp1) {
        this.contracttmp1 = contracttmp1;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public String getConfirmorName() {
        return confirmorName;
    }

    public void setConfirmorName(String confirmorName) {
        this.confirmorName = confirmorName;
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

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyDescribe() {
        return applyDescribe;
    }

    public void setApplyDescribe(String applyDescribe) {
        this.applyDescribe = applyDescribe;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrialAbstractName() {
        return trialAbstractName;
    }

    public void setTrialAbstractName(String trialAbstractName) {
        this.trialAbstractName = trialAbstractName;
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
}
