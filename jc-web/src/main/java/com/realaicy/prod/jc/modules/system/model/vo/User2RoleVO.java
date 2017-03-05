package com.realaicy.prod.jc.modules.system.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class User2RoleVO extends BaseVO<BigInteger> {

    @JsonProperty("title")
    private String name;
    @JsonProperty("realid")
    private BigInteger id;
    private boolean selected;

    @JsonProperty("hideCheckbox")
    public boolean isIfHideCheckbox() {
        return ifHideCheckbox;
    }

    public void setIfHideCheckbox(boolean ifHideCheckbox) {
        this.ifHideCheckbox = ifHideCheckbox;
    }

    private boolean ifHideCheckbox;

    /**
     * 资源是否是叶子节点
     */
    @Column(name = "IS_FOLDER")
    @JsonProperty("folder")
    private Boolean isFolder;

    public Boolean getFolder() {
        return isFolder;
    }

    public void setFolder(Boolean folder) {
        isFolder = folder;
    }

    private List<User2RoleVO> children;

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

    public List<User2RoleVO> getChildren() {
        return children;
    }

    public void setChildren(List<User2RoleVO> children) {
        this.children = children;
    }
}
