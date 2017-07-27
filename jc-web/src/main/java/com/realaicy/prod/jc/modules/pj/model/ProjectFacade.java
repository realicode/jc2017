package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * 项目门面实体
 */
@Entity
@Table(name = "jc_pj_facade")
public class ProjectFacade extends CommonDeletableEntity<BigInteger> {

    /**
     * 稽查名称
     */
    @Column(name = "PJ_NAME")
    @NotEmpty
    private String name;
    /**
     * 申办方机构名称
     */
    @Column(name = "PJ_SPONSOR_ORGNAME")
    @NotEmpty
    private String orgName;
    /**
     * 带稽查试验机构名称
     */
    @Column(name = "TRIALCENTERS")
    private String trialCenterNames;
    /**
     * 项目简要描述
     */
    @NotEmpty
    @Column(name = "PJ_DESCRIBE")
    private String projectDescribe;
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
     * 招募预稽查人员进度
     */
    @Column(name = "F_PUB_PRE")
    private Short pubPreStatus;
    @OneToOne()
    @JoinColumn(name = "PREINSPECTION_ID")
    private PreInspection preInspection;
    @OneToOne()
    @JoinColumn(name = "APPLICANT_ID")
    private User applyUser;

    public ProjectFacade() {
    }

    public ProjectFacade(Appliance appliance) {
        this.name = appliance.getName();
        this.orgName = appliance.getOrgName();
        this.trialCenterNames = appliance.getTrialCenterNames();
        this.projectDescribe =  appliance.getApplyDescribe();
        this.contractURI = appliance.getContractURI();
        this.trialURI = appliance.getTrialURI();
        this.applyUser = appliance.getUser();
        this.setPubPreStatus(Short.valueOf("0"));
    }

    public Short getPubPreStatus() {
        return pubPreStatus;
    }

    public void setPubPreStatus(Short pubPreStatus) {
        this.pubPreStatus = pubPreStatus;
    }

    public User getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(User applyUser) {
        this.applyUser = applyUser;
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

    public PreInspection getPreInspection() {
        return preInspection;
    }

    public void setPreInspection(PreInspection preInspection) {
        this.preInspection = preInspection;
    }
}
