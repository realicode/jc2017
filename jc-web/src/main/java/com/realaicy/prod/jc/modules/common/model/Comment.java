package com.realaicy.prod.jc.modules.common.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 通用评论类
 */
@Entity
@Table(name = "jc_sys_user")
public class Comment extends CommonDeletableEntity<BigInteger> {

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 评论所属主体ID
     */
    @JoinColumn(name = "BTID")
    private BigInteger btid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public BigInteger getBtid() {
        return btid;
    }

    public void setBtid(BigInteger btid) {
        this.btid = btid;
    }
}
