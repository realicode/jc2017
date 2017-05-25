package com.realaicy.prod.jc.modules.pj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckRunTime;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * 检查
 */
@Entity
@Table(name = "jc_pj_preinspection")
public class PreInspection extends CommonDeletableEntity<BigInteger> {


    /**
     * 稽查名称
     */
    @Column(name = "INSPECTIONNAME")
    @NotEmpty

    private String name;

    @Column(name = "SCORE")
    private int score;

    @ManyToOne
    @JoinColumn(name = "LEADER_ID")
    private User leader;

    @OneToOne
    @JoinColumn(name = "PJ_ID")
    private ProjectFacade projectFacade;
    @OneToOne
    @JoinColumn(name = "CHECKMODULE_ID")
    private PreCheckModule preCheckModuleRoot;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "SDATE")

    private Date preSDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "FDATE")

    private Date preFDate;
    @ManyToMany
    @JoinTable(name = "jc_pj_r_pre_user", joinColumns = @JoinColumn(name = "PJID"),
            inverseJoinColumns = @JoinColumn(name = "USERID"))
    private Set<User> user;
    @OneToMany(mappedBy = "preInspection")
    @OrderBy("checkModule")
    private Set<PreCheckRunTime> checkmodule;

    public PreCheckModule getPreCheckModuleRoot() {
        return preCheckModuleRoot;
    }

    public void setPreCheckModuleRoot(PreCheckModule preCheckModuleRoot) {
        this.preCheckModuleRoot = preCheckModuleRoot;
    }

    public Set<PreCheckRunTime> getCheckmodule() {
        return checkmodule;
    }

    public void setCheckmodule(Set<PreCheckRunTime> checkmodule) {
        this.checkmodule = checkmodule;
    }

    public Date getPreFDate() {
        return preFDate;
    }

    public void setPreFDate(Date preFDate) {
        this.preFDate = preFDate;
    }

    public Date getPreSDate() {
        return preSDate;
    }

    public void setPreSDate(Date preSDate) {
        this.preSDate = preSDate;
    }

    public ProjectFacade getProjectFacade() {
        return projectFacade;
    }

    public void setProjectFacade(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }


}
