package com.realaicy.prod.jc.modules.bi.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import java.math.BigInteger;

public class OrgRankVO extends BaseVO<BigInteger> {


    private String name;
    /**
     * weidu1
     */
    private String weidu1;
    /**
     * weidu2
     */
    private String weidu2;
    /**
     * weidu3
     */
    private String weidu3;
    /**
     * weidu3
     */
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
