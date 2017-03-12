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
public class Application extends CommonDeletableEntity<BigInteger> {


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
    @ManyToOne()
    @JoinColumn(name = "APPLICANT_ID")
    private User user;

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
