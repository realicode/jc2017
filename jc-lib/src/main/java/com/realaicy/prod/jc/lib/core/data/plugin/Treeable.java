package com.realaicy.prod.jc.lib.core.data.plugin;

import java.io.Serializable;

/**
 * <p>实体实现该接口表示想要实现树结构
 * <p/>
 *
 * @param <ID> the type parameter
 * @author realaicy
 * @version 1.0
 */
public interface Treeable<ID extends Serializable> {

    /**
     * Sets res icon.
     *
     * @param resIcon the res icon
     */
    void setResIcon(String resIcon);

    /**
     * Gets res icon ext.
     *
     * @return the res icon ext
     */
    String getResIconExt();

    /**
     * Sets res icon ext.
     *
     * @param resIconExt the res icon ext
     */
    void setResIconExt(String resIconExt);

    /**
     * Gets res weight.
     *
     * @return the res weight
     */
    Short getResWeight();

    /**
     * Sets res weight.
     *
     * @param resWeight the res weight
     */
    void setResWeight(Short resWeight);

    /**
     * Gets show.
     *
     * @return the show
     */
    Boolean getShow();

    /**
     * Sets show.
     *
     * @param show the show
     */
    void setShow(Boolean show);

    /**
     * Gets folder.
     *
     * @return the folder
     */
    Boolean getFolder();

    /**
     * Sets folder.
     *
     * @param folder the folder
     */
    void setFolder(Boolean folder);

    /**
     * Gets auto expand.
     *
     * @return the auto expand
     */
    Boolean getAutoExpand();

    /**
     * Sets auto expand.
     *
     * @param autoExpand the auto expand
     */
    void setAutoExpand(Boolean autoExpand);

    /**
     * Gets cascade id.
     *
     * @return the cascade id
     */
    String getCascadeID();

    /**
     * Sets cascade id.
     *
     * @param cascadeID the cascade id
     */
    void setCascadeID(String cascadeID);

}
