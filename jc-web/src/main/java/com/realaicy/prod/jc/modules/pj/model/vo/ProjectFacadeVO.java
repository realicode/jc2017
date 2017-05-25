package com.realaicy.prod.jc.modules.pj.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * 项目门面实体VO
 */
public class ProjectFacadeVO extends BaseVO<BigInteger> {

    private BigInteger id;
    /**
     * 稽查名称
     */
    @NotEmpty
    private String name;
    /**
     * 申办方机构名称
     */
    @NotEmpty
    private String orgName;
    /**
     * 带稽查试验机构名称
     */
    private String trialCenterNames;
    /**
     * 项目简要描述
     */
    @NotEmpty
    private String projectDescribe;
    /**
     * 合同URI
     */
    @NotEmpty
    private String contractURI;
    /**
     * 研究方案URI
     */
    @NotEmpty
    private String trialURI;
    /**
     * 申办方昵称
     */
    @NotEmpty
    private String applyusernickname;
    /**
     * 项目状态
     */
    @NotEmpty
    private Short projectStatus;

    /**
     * 预稽查人员招募状态
     */
    @NotEmpty
    private Short preRecruitStatus;

    /**
     * 项目进度
     */
    @NotEmpty
    private String projectProcess;
    /**
     * 招募预稽查人员进度
     */
    private Short pubPreStatus;

    public ProjectFacadeVO() {
    }

    public ProjectFacadeVO(ProjectFacade po) {
        this.id = po.getId();
        this.name = po.getName();
        this.projectStatus = po.getStatus();
        this.projectProcess = "50%";
        this.orgName = po.getOrgName();
        this.trialCenterNames = po.getTrialCenterNames();
        this.contractURI = po.getContractURI();
        this.trialURI = po.getTrialURI();
        this.pubPreStatus = po.getPubPreStatus();
    }

    public Short getPubPreStatus() {
        return pubPreStatus;
    }

    public void setPubPreStatus(Short pubPreStatus) {
        this.pubPreStatus = pubPreStatus;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProjectProcess() {
        return projectProcess;
    }

    public void setProjectProcess(String projectProcess) {
        this.projectProcess = projectProcess;
    }

    public Short getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Short projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getApplyusernickname() {
        return applyusernickname;
    }

    public void setApplyusernickname(String applyusernickname) {
        this.applyusernickname = applyusernickname;
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

    public String getTrialCenterNames() {
        return trialCenterNames;
    }

    public void setTrialCenterNames(String trialCenterNames) {
        this.trialCenterNames = trialCenterNames;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
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

    public Short getPreRecruitStatus() {
        return preRecruitStatus;
    }

    public void setPreRecruitStatus(Short preRecruitStatus) {
        this.preRecruitStatus = preRecruitStatus;
    }
}
