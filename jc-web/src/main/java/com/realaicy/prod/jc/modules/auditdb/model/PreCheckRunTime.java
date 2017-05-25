package com.realaicy.prod.jc.modules.auditdb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.system.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_check_precheck_runtime")
public class PreCheckRunTime extends CommonDeletableEntity<BigInteger> {

    @ManyToOne
    @JoinColumn(name = "PREINSPECTION_ID")
    private PreInspection preInspection;
    @OneToOne
    @JoinColumn(name = "CHECKMODULE_ID")
    private PreCheckModule checkModule;
    @Column(name = "RUNTIMENAME")
    private String name;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "CHECKREMARK")
    private String checkRemark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "DEADLINE")
    private Date deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name = "FDATE")
    private Date finishDate;
    @OneToOne
    @JoinColumn(name = "CHECKER_ID")
    private User checker;

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public PreInspection getPreInspection() {
        return preInspection;
    }

    public void setPreInspection(PreInspection preInspection) {
        this.preInspection = preInspection;
    }

    public PreCheckModule getCheckModule() {
        return checkModule;
    }

    public void setCheckModule(PreCheckModule checkModule) {
        this.checkModule = checkModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

}
