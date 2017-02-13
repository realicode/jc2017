package com.realaicy.prod.jc.lib.core.data.plugin;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>实体实现该接口表示具有公共属性
 * <p/>
 *
 * @author realaicy
 * @version 1.0
 */
public interface Commonable<ID extends Serializable> {
    Date getCreateTime();

    void setCreateTime(Date createTime);

    ID getCreaterID();

    void setCreaterID(ID createrID);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

    ID getUpdaterID();

    void setUpdaterID(ID updaterID);

    String getCustomCode();

    void setCustomCode(String customCode);

    short getStatus();

    void setStatus(short status);
}
