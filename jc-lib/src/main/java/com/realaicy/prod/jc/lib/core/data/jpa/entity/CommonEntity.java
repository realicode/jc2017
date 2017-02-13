package com.realaicy.prod.jc.lib.core.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Common entity.
 *
 * @param <ID> the type parameter
 */
@MappedSuperclass
public abstract class CommonEntity<ID extends Serializable> extends BaseEntity<ID>
        implements Commonable<ID> {


    @Column(name = "CREATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "CREATERID")
    private ID createrID;

    @Column(name = "UPDATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "UPDATERID")
    private ID updaterID;

    @Column(name = "CUSTOM_CODE")
    private String customCode = "";


    @Column(name = "STATUS")
    private short status;

    /**
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ID getCreaterID() {
        return createrID;
    }

    public void setCreaterID(ID createrID) {
        this.createrID = createrID;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ID getUpdaterID() {
        return updaterID;
    }

    public void setUpdaterID(ID updaterID) {
        this.updaterID = updaterID;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
