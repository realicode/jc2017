package com.realaicy.prod.jc.modules.pj.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItemRunTime;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.CHECKITEM_DONE;

/**
 * 项目门面实体VO
 */
public class PreInspectionShowVO extends BaseVO<BigInteger> {

    private String projectName;
    private String leaderName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date preSDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date preFDate;
    private int score;
    private List<PreInspectionCheckItemRuntimeVO> checkitems = new ArrayList<>();

    public PreInspectionShowVO(PreInspection po) {

        this.projectName = po.getProjectFacade().getName();
        this.leaderName = po.getLeader().getNickname();

        for (PreCheckItemRunTime preCheckItemRunTime : po.getCheckitem()) {
            PreInspectionCheckItemRuntimeVO tmp = new PreInspectionCheckItemRuntimeVO();
            tmp.setName(preCheckItemRunTime.getCheckitem().getName());
            tmp.setCheckername(preCheckItemRunTime.getChecker().getNickname());
            tmp.setStatus(preCheckItemRunTime.getStatus());
            if (Objects.equals(tmp.getStatus(), CHECKITEM_DONE)) {
                tmp.setFinishDate(preCheckItemRunTime.getFinishDate());
                tmp.setCheckRemark(preCheckItemRunTime.getCheckRemark());
                tmp.setScore(preCheckItemRunTime.getScore());
            }
            checkitems.add(tmp);
        }
    }

    public PreInspectionShowVO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Date getPreSDate() {
        return preSDate;
    }

    public void setPreSDate(Date preSDate) {
        this.preSDate = preSDate;
    }

    public Date getPreFDate() {
        return preFDate;
    }

    public void setPreFDate(Date preFDate) {
        this.preFDate = preFDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<PreInspectionCheckItemRuntimeVO> getCheckitems() {
        return checkitems;
    }

    public void setCheckitems(List<PreInspectionCheckItemRuntimeVO> checkitems) {
        this.checkitems = checkitems;
    }
}
