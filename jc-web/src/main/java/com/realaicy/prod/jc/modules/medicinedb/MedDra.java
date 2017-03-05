package com.realaicy.prod.jc.modules.medicinedb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
/**
 * Meddra
 */
@Entity
@Table(name = "jc_m_db_meddra")
public class MedDra extends CommonEntity<BigInteger> {

    /**
     * 资源名称
     */
    @Column(name = "NAME")
    @JsonProperty("title")
    private String name;

    /**
     * 资源LEVEL
     */
    @Column(name = "LEVEL")
    private String level;

    /**
     * 资源CODE
     */
    @Column(name = "CODE")
    private BigInteger code;
    /**
     * 资源父CODE
     */
    @Column(name = "PCODE")
    private BigInteger pCode;
    /**
     * 资源是否是叶子节点
     */
    @Column(name = "IS_FOLDER")
    @JsonProperty("folder")
    private Boolean folder;
    /**
     * 资源是否是叶子节点
     */
    @Column(name = "LAZY")
    @JsonProperty("lazy")
    private Boolean lazy;
    /**
     * 资源标识字符串(对应用户所持有的权限字符串)
     */
    @Column(name = "IDENTITY")
    private String resIdentity = "";

    public Boolean getFolder() {
        return folder;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }

    public BigInteger getpCode() {
        return pCode;
    }

    public void setpCode(BigInteger pCode) {
        this.pCode = pCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResIdentity() {
        return resIdentity;
    }

    public void setResIdentity(String resIdentity) {
        this.resIdentity = resIdentity;
    }
}
