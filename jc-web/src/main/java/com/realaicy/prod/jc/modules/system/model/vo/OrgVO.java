package com.realaicy.prod.jc.modules.system.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class OrgVO extends BaseVO<BigInteger> {

    /**
     * 名称
     */
    @NotEmpty
    private String name;

    /**
     * 别名1
     */
    private String nameAlias1;
    /**
     * 别名2
     */
    private String nameAlias2;
    /**
     * 所属区域
     */
    @NotEmpty
    private String region;
    /**
     * 所属省份
     */
    @NotEmpty
    private String province;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系人姓名
     */
    private String contactName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlias1() {
        return nameAlias1;
    }

    public void setNameAlias1(String nameAlias1) {
        this.nameAlias1 = nameAlias1;
    }

    public String getNameAlias2() {
        return nameAlias2;
    }

    public void setNameAlias2(String nameAlias2) {
        this.nameAlias2 = nameAlias2;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
