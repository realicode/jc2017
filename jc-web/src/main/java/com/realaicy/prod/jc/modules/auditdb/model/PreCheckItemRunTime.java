package com.realaicy.prod.jc.modules.auditdb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionCheckItemRuntimeVO;
import com.realaicy.prod.jc.modules.system.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_check_precheckitem_runtime")
public class PreCheckItemRunTime extends CommonDeletableEntity<BigInteger> {

    @ManyToOne
    @JoinColumn(name = "PREINSPECTION_ID")
    private PreInspection preInspection;
    @OneToOne
    @JoinColumn(name = "CHECKITEM_ID")
    private PreCheckItem checkitem;
    @Column(name = "RUNTIMENAME")
    private String name;

    @Column(name = "SCORE")
    private int score;

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


    public PreInspection getPreInspection() {
        return preInspection;
    }

    public void setPreInspection(PreInspection preInspection) {
        this.preInspection = preInspection;
    }

    public PreCheckItem getCheckitem() {
        return checkitem;
    }

    public void setCheckitem(PreCheckItem checkitem) {
        this.checkitem = checkitem;
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
