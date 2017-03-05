package com.realaicy.prod.jc.modules.auditdb.model.vo;


import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class AuditCheckItemVO extends BaseVO<BigInteger> {

    /**
     * 检查项名称
     */
    @NotNull
    private String name;
    private Boolean folder;

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
