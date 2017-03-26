package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class FinalVO extends BaseVO<BigInteger> {


    /**
     * 最终意见
     */
    @NotEmpty
    private String finalRemark;

    /**
     * id
     */
    @NotEmpty
    private BigInteger applyid;

    public String getFinalRemark() {
        return finalRemark;
    }

    public void setFinalRemark(String finalRemark) {
        this.finalRemark = finalRemark;
    }

    public BigInteger getApplyid() {
        return applyid;
    }

    public void setApplyid(BigInteger applyid) {
        this.applyid = applyid;
    }
}
