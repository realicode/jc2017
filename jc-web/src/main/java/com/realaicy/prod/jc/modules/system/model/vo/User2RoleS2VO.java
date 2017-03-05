package com.realaicy.prod.jc.modules.system.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class User2RoleS2VO extends BaseVO<BigInteger> {

    @JsonProperty("text")
    private String name;
    @JsonProperty("id")
    private BigInteger id;
    private boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
