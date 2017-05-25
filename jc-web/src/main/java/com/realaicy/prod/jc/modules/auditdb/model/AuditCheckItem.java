package com.realaicy.prod.jc.modules.auditdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonTreeableDeletableEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_audit_checkitem")
public class AuditCheckItem extends CommonTreeableDeletableEntity<BigInteger, AuditCheckItem> {

    /**
     * 资源类型
     */
    @Column(name = "RES_TYPE")
    private short resType;

    /**
     * 资源名称
     */
    @Column(name = "NAME")
    @JsonProperty("title")
    private String name;

    /**
     * 资源标识字符串(对应用户所持有的权限字符串)
     */
    @Column(name = "IDENTITY")
    private String resIdentity = "";

    /**
     * 孩子菜单对象
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    @OrderBy("resWeight")
    private List<AuditCheckItem> children = new ArrayList<>();

    public short getResType() {
        return resType;
    }

    public void setResType(short resType) {
        this.resType = resType;
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

    public List<AuditCheckItem> getChildren() {
        return children;
    }

    public void setChildren(List<AuditCheckItem> children) {
        this.children = children;
    }

}
