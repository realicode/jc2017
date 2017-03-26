package com.realaicy.prod.jc.modules.bi.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_bi_rank_org")
public class OrgRank extends CommonDeletableEntity<BigInteger> {

    /**
     * weidu1
     */
    @Column(name = "ORGRANKNAME")
    private String name;
    /**
     * weidu1
     */
    @Column(name = "WEIDU_1")
    private String weidu1;
    /**
     * weidu2
     */
    @Column(name = "WEIDU_2")
    private String weidu2;
    /**
     * weidu3
     */
    @Column(name = "WEIDU_3")
    private String weidu3;
    /**
     * weidu3
     */
    @Column(name = "RANK_SCORE")
    private String rankScore;

    public String getRankScore() {
        return rankScore;
    }

    public void setRankScore(String rankScore) {
        this.rankScore = rankScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeidu1() {
        return weidu1;
    }

    public void setWeidu1(String weidu1) {
        this.weidu1 = weidu1;
    }

    public String getWeidu2() {
        return weidu2;
    }

    public void setWeidu2(String weidu2) {
        this.weidu2 = weidu2;
    }

    public String getWeidu3() {
        return weidu3;
    }

    public void setWeidu3(String weidu3) {
        this.weidu3 = weidu3;
    }


}
