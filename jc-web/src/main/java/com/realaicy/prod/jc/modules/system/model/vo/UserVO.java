package com.realaicy.prod.jc.modules.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.modules.system.model.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class UserVO extends BaseVO<BigInteger> {
    private BigInteger id;
    /**
     * 用户名称
     */
    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;
    /**
     * 用户昵称
     */
    @NotEmpty
    @Size(min = 2, max = 20)
    private String nickname;

    public String getWxUserID() {
        return wxUserID;
    }

    public void setWxUserID(String wxUserID) {
        this.wxUserID = wxUserID;
    }

    /**
     * 用户密码(明文)
     */
    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;
    /**
     * 用户邮箱地址
     */
    @NotEmpty
    @Email
    private String email;
    /**
     * 用户手机号码
     */
    @NotEmpty
    @Pattern(regexp = "^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$")
    private String mobile;
    /**
     * 所属机构名称
     */
    private String orgName;
    /**
     * 所属机构ID
     */
    @NotEmpty
    private String orgID;
    /**
     * 所属机构ID
     */
    private String orgRegion;
    /**
     * 所属机构ID
     */
    private String orgProvince;
    /**
     * 用户头像
     */
    private String portraitUrl;

    /**
     * 微信
     */
    private String wxUserID;

    /**
     * 用户性别
     */
    private char sex;
    /**
     * 用户角色名称集合（以，分隔开）
     */
    private String roleNames;
    /**
     * 用户角色ID集合（以，分隔开）
     */
    private String roleIDs;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    public UserVO() {
    }

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getUserInfo().getEmail();
        this.orgRegion = user.getOrg().getRegion();
        this.orgProvince = user.getOrg().getProvince();
        this.orgName = user.getOrg().getName();
        this.mobile = user.getUserInfo().getMobile();
        this.createTime = user.getCreateTime();

    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getRoleIDs() {
        return roleIDs;
    }

    public void setRoleIDs(String roleIDs) {
        this.roleIDs = roleIDs;
    }

    public String getOrgRegion() {
        return orgRegion;
    }

    public void setOrgRegion(String orgRegion) {
        this.orgRegion = orgRegion;
    }

    public String getOrgProvince() {
        return orgProvince;
    }

    public void setOrgProvince(String orgProvince) {
        this.orgProvince = orgProvince;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
