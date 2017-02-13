package com.realaicy.prod.jc.lib.core.data.plugin;

/**
 * <p>实体实现该接口表示想要逻辑删除
 * 为了简化开发 约定删除标识列名为deleted，<br/>
 * <p/>
 *
 * @author realaicy
 * @version 1.0
 */
public interface LogicDeletable {

    /**
     * 标识为已删除
     */
     void markDeleted();

    /**
     * 标识为已删除
     */
     void markUnDeleted();

}
