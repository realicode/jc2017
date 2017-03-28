package com.realaicy.prod.jc.modules.pj.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

/**
 * 检查
 */
@Entity
@Table(name = "jc_pj_preinspection")
public class PreInspection extends CommonDeletableEntity<BigInteger> {


    /**
     * 稽查名称
     */
    @Column(name = "INSPECTIONNAME")
    @NotEmpty
    private String name;


    @ManyToMany
    @JoinTable(name = "jc_pj_r_pre_user", joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "PJID"))
    private Set<User> user;


}
