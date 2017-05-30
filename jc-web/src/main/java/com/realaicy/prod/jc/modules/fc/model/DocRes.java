package com.realaicy.prod.jc.modules.fc.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonTreeableDeletableEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 文档实体类
 */
@Entity
@Table(name = "jc_m_doc_allinone")
@JsonFilter("realFilter")
public class DocRes extends CommonTreeableDeletableEntity<BigInteger, DocRes> {


    /**
     * 所属角色、人员的ID
     */
    @Column(name = "BTID")
    private BigInteger belongID;
    /**
     * 资源类型
     */
    @Column(name = "RES_TYPE_SUB")
    private short resBelongType;
    /**
     * 资源类型
     */
    @Column(name = "RES_TYPE")
    private short resType;
    /**
     * 角色所属组织
     */
    @Column(name = "ORGID")
    private BigInteger orgID;

    /**
     * 是否是组织根目录
     */
    @Column(name = "F_ORGROOT")
    private Boolean orgRootFlag;
    /**
     * 资源名称
     */
    @Column(name = "NAME")
    @JsonProperty("title")
    private String name;
    /**
     * 资源URI
     */
    @Column(name = "URI")
    private String uri;
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
    private List<DocRes> children = new ArrayList<>();

    public BigInteger getBelongID() {
        return belongID;
    }

    public void setBelongID(BigInteger belongID) {
        this.belongID = belongID;
    }

    public short getResBelongType() {
        return resBelongType;
    }

    public void setResBelongType(short resBelongType) {
        this.resBelongType = resBelongType;
    }

    public BigInteger getOrgID() {
        return orgID;
    }

    public void setOrgID(BigInteger orgID) {
        this.orgID = orgID;
    }

    public Boolean getOrgRootFlag() {
        return orgRootFlag;
    }

//    /**
//     * 父亲菜单对象
//     */
//    @ManyToOne
//    @JoinColumn(name = "PID")
//    @JsonIgnore
//    private DocRes parent;

    public void setOrgRootFlag(Boolean orgRootFlag) {
        this.orgRootFlag = orgRootFlag;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResIdentity() {
        return resIdentity;
    }

    public void setResIdentity(String resIdentity) {
        this.resIdentity = resIdentity;
    }

    public List<DocRes> getChildren() {
        return children;
    }

    public void setChildren(List<DocRes> children) {
        this.children = children;
    }

}
