package com.realaicy.prod.jc.lib.core.data.jpa.entity;



import com.realaicy.prod.jc.lib.core.data.plugin.LogicDeletable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * <p> 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * 如果是oracle请参考{@link BaseOracleEntity}
 * <p>Date: 13-1-12 下午4:05
 * <p>Version: 1.0
 *
 * @param <ID> the type parameter
 * @param <M>  the type parameter
 */
@MappedSuperclass
public abstract class CommonTreeableDeletableEntity<ID extends Serializable, M extends CommonEntity<ID>>
        extends CommonTreeableEntity<ID, M> implements LogicDeletable {


    public void markDeleted() {
        this.setDeleteFlag(true);
    }


    public void markUnDeleted() {
        this.setDeleteFlag(false);
    }

    /**
     * 逻辑删除标志
     */
    @Column(name = "F_DELETED")
    private Boolean deleteFlag = false;

    /**
     * Gets delete flag.
     *
     * @return the delete flag
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets delete flag.
     *
     * @param deleteFlag the delete flag
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
