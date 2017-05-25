package com.realaicy.prod.jc.modules.auditdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_check_precheckitem")
public class PreCheckItem extends CommonDeletableEntity<BigInteger> {

    /**
     * 资源名称
     */
    @Column(name = "NAME")
    @JsonProperty("title")
    private String name;

    /**         ]
     * 模块模版ID
     */
    @Column(name = "MODULE_ID")
    private BigInteger moduleID;

    /**
     * 资源标识字符串(对应用户所持有的权限字符串)
     */
    @Column(name = "IDENTITY")
    private String resIdentity = "";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getModuleID() {
        return moduleID;
    }

    public void setModuleID(BigInteger moduleID) {
        this.moduleID = moduleID;
    }

    public String getResIdentity() {
        return resIdentity;
    }

    public void setResIdentity(String resIdentity) {
        this.resIdentity = resIdentity;
    }
}
