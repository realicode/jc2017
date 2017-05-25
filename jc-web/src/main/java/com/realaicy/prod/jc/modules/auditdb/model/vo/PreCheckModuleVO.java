package com.realaicy.prod.jc.modules.auditdb.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class PreCheckModuleVO extends BaseVO<BigInteger> {
    /**
     * 检查模块名称
     */
    @NotNull
    private String name;
    private Boolean folder;
    private Short resWeight;

    public Short getResWeight() {
        return resWeight;
    }

    public void setResWeight(Short resWeight) {
        this.resWeight = resWeight;
    }

    public Boolean getFolder() {
        return folder;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
