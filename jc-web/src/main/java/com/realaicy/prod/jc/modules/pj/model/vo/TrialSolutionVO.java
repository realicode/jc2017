package com.realaicy.prod.jc.modules.pj.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class TrialSolutionVO extends BaseVO<BigInteger> {


    /**
     * 临时测试
     */
    @NotEmpty
    private String contracttmp2;

    /**
     * id
     */
    @NotEmpty
    private BigInteger applyid;

    /**
     * 合同URI
     */
    @NotEmpty
    private String trialURI;

    public String getTrialURI() {
        return trialURI;
    }

    public void setTrialURI(String trialURI) {
        this.trialURI = trialURI;
    }

    public BigInteger getApplyid() {
        return applyid;
    }

    public void setApplyid(BigInteger applyid) {
        this.applyid = applyid;
    }

    public String getContracttmp2() {
        return contracttmp2;
    }

    public void setContracttmp2(String contracttmp2) {
        this.contracttmp2 = contracttmp2;
    }


}
