package com.realaicy.prod.jc.modules.auditdb.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class PreCheckRunTImeVO extends BaseVO<BigInteger> {
    /**
     * 检查模块名称
     */
    @NotNull
    @JsonProperty("title")
    private String name;
    @JsonProperty("key")
    private BigInteger id;
    private Boolean folder;
    private Short resWeight;
    private List<PreCheckRunTImeVO> children = new ArrayList<>();
    private int score;
    private String comment;
    private String checkerName;

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public List<PreCheckRunTImeVO> getChildren() {
        return children;
    }

    public void setChildren(List<PreCheckRunTImeVO> children) {
        this.children = children;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Short getResWeight() {
        return resWeight;
    }

    public void setResWeight(Short resWeight) {
        this.resWeight = resWeight;
    }

    public Boolean getFolder() {
        return folder;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
