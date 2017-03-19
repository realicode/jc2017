package com.realaicy.prod.jc.modules.system.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 用户安全类
 */
@Entity
@Table(name = "jc_real_test")
public class RealTest extends BaseEntity<BigInteger> {

    /**
     * 用户名称
     */
    @Column(name = "E_NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
