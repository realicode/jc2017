package com.realaicy.prod.jc.modules.auditdb.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 稽查检查项类
 */
@Entity
@Table(name = "jc_m_check_precheckitem")
public class PreCheckItem extends BaseEntity<BigInteger> {


    @Column(name = "CHECKITEMNAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
