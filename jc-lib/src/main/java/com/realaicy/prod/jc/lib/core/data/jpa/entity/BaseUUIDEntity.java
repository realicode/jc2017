package com.realaicy.prod.jc.lib.core.data.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * <p> 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * 如果是oracle请参考{@link BaseOracleEntity}
 * <p>Date: 13-1-12 下午4:05
 * <p>Version: 1.0
 *
 * @param <ID> the type parameter
 */
@MappedSuperclass
public abstract class BaseUUIDEntity<ID extends Serializable> extends AbstractEntity<ID> {

    /**
     * xxx
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "uuid2",
            parameters = {
                    @Parameter(
                            name = UUIDGenerator.UUID_GEN_STRATEGY_CLASS,
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id")
    private ID id;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

}
