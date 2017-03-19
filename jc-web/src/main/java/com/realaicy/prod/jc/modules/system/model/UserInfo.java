package com.realaicy.prod.jc.modules.system.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/**
 * 用户实体类
 */
@Entity
@Table(name = "jc_sys_userinfo")
public class UserInfo extends CommonDeletableEntity<BigInteger> {

    /**
     * 用户昵称
     */
    @NotEmpty
    @Column(name = "NICKNAME")
    @Size(min = 2, max = 20)
    private String nickname;

    /**
     * 用户邮箱地址
     */
    @Column(name = "EMAIL")
    @Email
    private String email;

    /**
     * 用户手机
     */
    @Column(name = "MOBILE")
    @Pattern(regexp = "^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$")
    private String mobile;

    /**
     * 用户年龄
     */
    @Column(name = "AGE")
    private short age;

    /**
     * 用户头像地址
     */
    @Column(name = "R_TOUXIANG")
    private String portraitUrl;

    /**
     * 用户微信ID
     */
    @Column(name = "WX_USERID")
    private String wxUserID;

    /**
     * 用户性别
     */
    @Column(name = "SEX")
    private char sex;

    /**
     * 用户类型
     */
    @Column(name = "USERTYPE")
    private short usertype;

    /**
     * 用户角色名字
     */
    @Column(name = "ROLENAMES")
    private String rolenames;
    /**
     * 用户其他信息
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userInfo")
    @JoinColumn(name = "USER_ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWxUserID() {
        return wxUserID;
    }

    public void setWxUserID(String wxUserID) {
        this.wxUserID = wxUserID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public short getUsertype() {
        return usertype;
    }

    public void setUsertype(short usertype) {
        this.usertype = usertype;
    }

}
