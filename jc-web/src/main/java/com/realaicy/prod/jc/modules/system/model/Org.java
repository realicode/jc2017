package com.realaicy.prod.jc.modules.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_sys_org")
public class Org extends CommonDeletableEntity<BigInteger> {

    /**
     * 类型
     */
    @Column(name = "ORG_TYPE")
    private String type;
    /**
     * 名称
     */
    @Column(name = "ORG_NAME", unique = true)
    @NotEmpty
    private String name;
    /**
     * 别名1
     */
    @Column(name = "ALIAS1")
    private String nameAlias1;
    /**
     * 别名2
     */
    @Column(name = "ALIAS2")
    private String nameAlias2;
    /**
     * 所属区域
     */
    @NotEmpty
    @Column(name = "ORG_REGION")
    private String region;
    /**
     * 所属省份
     */
    @NotEmpty
    @Column(name = "ORG_PROVINCE")
    private String province;
    /**
     * 地址
     */
    @Column(name = "ORG_ADDRESS")
    private String address;
    /**
     * 联系人姓名
     */
    @Column(name = "CONTACT_NAME")
    private String contactName;


    @OneToMany(mappedBy = "org")
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlias1() {
        return nameAlias1;
    }

    public void setNameAlias1(String nameAlias1) {
        this.nameAlias1 = nameAlias1;
    }

    public String getNameAlias2() {
        return nameAlias2;
    }

    public void setNameAlias2(String nameAlias2) {
        this.nameAlias2 = nameAlias2;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


}
