package com.realaicy.prod.jc.lib.core.data.jpa.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Persistable;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 抽象实体基类，如果主键是数据库端自动生成 请使用{
 *
 * @param <ID> the type parameter
 */
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

    /**
     * HASHCODENUM
     */
    private static final int HASHCODENUM = 17;

    /**
     * HASHCODENUM2
     */
    private static final int HASHCODENUM2 = 31;

    /**
     * Gets id.
     *
     * @return the id
     */
    @XmlTransient
    public abstract ID getId();

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public abstract void setId(final ID id);


    /**
     * {@inheritDoc}
     */
    public boolean isNew() {

        return null == getId();
    }

    /**
     * {@inheritDoc}
     */ /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        //return null == this.getId() ? false : this.getId().equals(that.getId());
        return null != this.getId() && this.getId().equals(that.getId());
    }

    /**
     * 重写hashCode
     *
     * @return XXX
     */
    @Override
    public int hashCode() {

        int hashCode = HASHCODENUM;

        hashCode += null == getId() ? 0 : getId().hashCode() * HASHCODENUM2;

        return hashCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
