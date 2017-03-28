package com.realaicy.prod.jc.modules.pj.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.math.BigInteger;

/**
 * 项目门面实体VO
 */
public class PreInspectionUserVO extends BaseVO<BigInteger> {

    private BigInteger id;
    private String nickname;

    public PreInspectionUserVO(BigInteger id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
