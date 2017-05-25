package com.realaicy.prod.jc.modules.pj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * 项目门面实体
 */
@Entity
@Table(name = "jc_m_recruit_supply")
public class RecruitSupply extends CommonDeletableEntity<BigInteger> {

    /**
     * 招募工作的类型
     */
    @Column(name = "WORKTYPE")
    @NotEmpty
    private String workType;
    /**
     * 招募工作的类型
     */
    @Column(name = "COMMENT")
    private String comment;
    @OneToOne
    @JoinColumn(name = "PJ_ID")
    private ProjectFacade projectFacade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "DEADLINE")
    private Date deadLine;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "ESTSTARTDATE")
    private Date estStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "ESTENDDATE")
    private Date estEndDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jc_m_recruit_supply_apply", joinColumns = @JoinColumn(name = "SUPPLYID"),
            inverseJoinColumns = @JoinColumn(name = "USERID"))
    private Set<User> applys;

    public RecruitSupply() {
    }

    public Set<User> getApplys() {
        return applys;
    }

    public void setApplys(Set<User> applys) {
        this.applys = applys;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public ProjectFacade getProjectFacade() {
        return projectFacade;
    }

    public void setProjectFacade(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getEstStartDate() {
        return estStartDate;
    }

    public void setEstStartDate(Date estStartDate) {
        this.estStartDate = estStartDate;
    }

    public Date getEstEndDate() {
        return estEndDate;
    }

    public void setEstEndDate(Date estEndDate) {
        this.estEndDate = estEndDate;
    }
}
