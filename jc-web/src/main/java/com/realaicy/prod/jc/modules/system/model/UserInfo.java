package com.realaicy.prod.jc.modules.system.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

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
     * 用户职位
     */
    @Column(name = "USER_TITLE")
    private String userTitle;
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
    private String wxuserid;
    /**
     * 用户性别
     */
    @Column(name = "SEX")
    private char sex;
    /**
     * 用户积分
     */
    @Column(name = "POINTS")
    private BigInteger points;
    /**
     * 连续签到天数
     */
    @Column(name = "CONSIGNDAYS")
    private int contiSignDays;
    /**
     * 上一次签到日期
     */
    @Column(name = "LASTSIGNDAY")
    private Date lastSignDay;
    /**
     * 上一次登录
     */
    @Column(name = "LASTLOGIN")
    private Date lastLogin;
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

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public int getContiSignDays() {
        return contiSignDays;
    }

    public void setContiSignDays(int contiSignDays) {
        this.contiSignDays = contiSignDays;
    }

    public Date getLastSignDay() {
        return lastSignDay;
    }

    public void setLastSignDay(Date lastSignDay) {
        this.lastSignDay = lastSignDay;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    public String getWxuserid() {
        return wxuserid;
    }

    public void setWxuserid(String wxuserid) {
        this.wxuserid = wxuserid;
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
