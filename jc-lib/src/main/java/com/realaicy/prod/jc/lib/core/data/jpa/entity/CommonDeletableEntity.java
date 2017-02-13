package com.realaicy.prod.jc.lib.core.data.jpa.entity;



import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.data.plugin.LogicDeletable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by realaicy on 2016/10/9.
 * xx
 *
 * @param <ID> the type parameter
 */
@MappedSuperclass
public abstract class CommonDeletableEntity<ID extends Serializable> extends CommonEntity<ID>
        implements Commonable<ID>, LogicDeletable {
    /**
     * 逻辑删除标志
     */
    @Column(name = "F_DELETED")
    private Boolean deleteFlag = false;

    @Override
    public void markDeleted() {
        this.setDeleteFlag(true);
    }

    @Override
    public void markUnDeleted() {
        this.setDeleteFlag(false);
    }

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
