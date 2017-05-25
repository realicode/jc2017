package com.realaicy.prod.jc.modules.auditdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonTreeableDeletableEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_check_precheckmodule")
public class PreCheckModule extends CommonTreeableDeletableEntity<BigInteger, PreCheckModule> {


    /**
     * XXX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    @JsonProperty("key")
    private BigInteger id;
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
    /**         ]
     * 模版ID
     */
    @Column(name = "TEM_ID")
    private BigInteger tem;
    /**         ]
     * 模版ID
     */
    @Column(name = "TEM_NAME")
    private String temName;
    /**
     * 是否是ROOT

     */
    @Column(name = "IS_ROOT")
    private Boolean rootlevel;
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
    @Where(clause = "F_DELETED='0'")
    private List<PreCheckModule> children = new ArrayList<>();

    public String getTemName() {
        return temName;
    }

    public void setTemName(String temName) {
        this.temName = temName;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getTem() {
        return tem;
    }

    public void setTem(BigInteger tem) {
        this.tem = tem;
    }

    public Boolean getRootlevel() {
        return rootlevel;
    }

    public void setRootlevel(Boolean rootlevel) {
        this.rootlevel = rootlevel;
    }

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

    public List<PreCheckModule> getChildren() {
        return children;
    }

    public void setChildren(List<PreCheckModule> children) {
        this.children = children;
    }
}
