package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class ApplicationVO extends BaseVO<BigInteger> {

    /**
     * ID
     */
    @NotEmpty
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
     * 带稽查试验机构名称
     */
    @NotEmpty
    private String trialCenterNames;
    /**
     * 申请人
     */
    @NotEmpty
    private String applicantName;

    public ApplicationVO() {
    }

    public ApplicationVO(Application po) {
        this.id = po.getId();
        this.name = po.getName();
        this.orgName = po.getOrgName();
        this.trialAbstractURI = po.getTrialAbstractURI();
        this.trialAbstractName = po.getTrialAbstractName();
        this.trialCenterNames = po.getTrialCenterNames();
        this.applicantName = po.getUser().getNickname();
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
