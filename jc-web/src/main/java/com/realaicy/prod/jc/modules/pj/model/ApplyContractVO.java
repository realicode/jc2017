package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class ApplyContractVO extends BaseVO<BigInteger> {


    /**
     * 临时测试
     */
    @NotEmpty
    private String contracttmp1;

    /**
     * id
     */
    @NotEmpty
    private BigInteger applyid;

    /**
     * 合同URI
     */
    @NotEmpty
    private String contractURI;

    private String realaicytest;

    public String getRealaicytest() {
        return realaicytest;
    }

    public void setRealaicytest(String realaicytest) {
        this.realaicytest = realaicytest;
    }

    public ApplyContractVO() {
    }

    public String getContractURI() {
        return contractURI;
    }

    public void setContractURI(String contractURI) {
        this.contractURI = contractURI;
    }

    public BigInteger getApplyid() {
        return applyid;
    }

    public void setApplyid(BigInteger applyid) {
        this.applyid = applyid;
    }

    public String getContracttmp1() {
        return contracttmp1;
    }

    public void setContracttmp1(String contracttmp1) {
        this.contracttmp1 = contracttmp1;
    }


}
