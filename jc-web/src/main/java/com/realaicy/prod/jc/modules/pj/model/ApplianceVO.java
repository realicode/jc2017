package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.hibernate.validator.constraints.NotEmpty;

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
    private String name;
    /**
     * 申办方机构名称
     */
    @NotEmpty
    private String orgName;
    /**
     * 方案摘要URI
     */
    @NotEmpty
    private String trialAbstractURI;
    @NotEmpty
    private String trialAbstractName;
    /**
     * 待稽查试验机构名称
     */
    @NotEmpty
    private String trialCenterNames;
    /**
     * 申请状态
     */
    private String applayStatus;
    /**
     * 申请的简要描述
     */
    @NotEmpty
    private String applyDescribe;
    /**
     * 申请人
     */
    private String applicantName;
    /**
     * "确认申请"所需的描述
     */
    private String confirmRemark;
    /**
     * 报价
     */
    private Integer quotation;
    /**
     * 按钮显示方式
     */
    private String btnType;

    public ApplianceVO() {
    }

    public ApplianceVO(Appliance po) {
        this.id = po.getId();
        this.name = po.getName();
        this.orgName = po.getOrgName();
        this.trialAbstractURI = po.getTrialAbstractURI();
        this.trialAbstractName = po.getTrialAbstractName();
        this.trialCenterNames = po.getTrialCenterNames();
        this.applicantName = po.getUser().getNickname();
        this.applayStatus = String.valueOf(po.getStatus());
        this.applyDescribe = po.getApplyDescribe();
        this.confirmRemark = po.getConfirmRemark();
        if (SpringSecurityUtil.hasPrivilege(Appliance.class.getSimpleName() + "-ack")) {
            this.btnType = "2"; //有确认按钮
        }
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

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getApplayStatus() {
        return applayStatus;
    }

    public void setApplayStatus(String applayStatus) {
        this.applayStatus = applayStatus;
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
