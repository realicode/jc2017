package com.realaicy.prod.jc.modules.common.model;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * 系统角色实体类
 */
@Entity
@Table(name = "jc_sys_role")
public class File extends CommonDeletableEntity<BigInteger> {


    /**
     * 名称（路径）
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 显示名称
     */
    @Column(name = "SHOWNAME")
    private String nickname;
    /**
     *  文件类型
     */
    @Column(name = "FILETYPE")
    private String filetype;

    /**
     * 文件所属主体ID
     */
    @JoinColumn(name = "BTID")
    private BigInteger btid;

}
