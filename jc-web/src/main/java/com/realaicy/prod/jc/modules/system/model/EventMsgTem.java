package com.realaicy.prod.jc.modules.system.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_event_msgtem")
public class EventMsgTem extends CommonDeletableEntity<BigInteger> {

    /**
     * 名称
     */
    @Column(name = "MSGCONTENT")
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
