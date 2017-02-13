package com.realaicy.prod.jc.modules.demo.model;

/**
 * Created by realaicy on 16/7/16.
 *
 * @author Realaicy
 */

import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.IOrgRestricted;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * 测试实体类
 */
@Entity
@Table(name = "jc_common_test")
public class DemoEntity extends CommonDeletableEntity<BigInteger> implements IOrgRestricted {


    /**
     * 所属租户
     */
    @Column(name = "TENANTID")
    private BigInteger tenantID;

    /**
     * 所属组织
     */
    @Column(name = "ORGID")
    private BigInteger orgID;

    /**
     * 用户名称
     */
    @Column(name = "USERNAME")
    @NotEmpty
    private String username;
    /**
     * 用户密码(加密后的密文)
     */
    @NotEmpty
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 用户昵称
     */
    @NotEmpty
    @Column(name = "NICKNAME")
    private String nickname;
    /**
     * 用户邮箱地址
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * 用户年龄
     */
    @Column(name = "AGE")
    private short age;
    /**
     * 用户年龄
     */
    @Column(name = "R_TOUXIANG")
    private String portraitUrl;
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
     * 用户所属组织语义ID
     */
    @Column(name = "ORGCASID")
    private String orgCascadeID;


    /**
     * 用户类型
     */
    @Column(name = "ROLENAMES")
    private String rolenames;


    /**
     * Gets rolenames.
     *
     * @return the rolenames
     */
    public String getRolenames() {
        return rolenames;
    }

    /**
     * Sets rolenames.
     *
     * @param rolenames the rolenames
     */
    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    /**
     * Gets org id.
     *
     * @return the org id
     */
    public BigInteger getOrgID() {
        return orgID;
    }

    /**
     * Sets org id.
     *
     * @param orgID the org id
     */
    public void setOrgID(BigInteger orgID) {
        this.orgID = orgID;
    }

    /**
     * Gets tenant id.
     *
     * @return the tenant id
     */
    public BigInteger getTenantID() {
        return tenantID;
    }

    /**
     * Sets tenant id.
     *
     * @param tenantID the tenant id
     */
    public void setTenantID(BigInteger tenantID) {
        this.tenantID = tenantID;
    }

    /**
     * Gets portrait url.
     *
     * @return the portrait url
     */
    public String getPortraitUrl() {
        return portraitUrl;
    }

    /**
     * Sets portrait url.
     *
     * @param portraitUrl the portrait url
     */
    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public short getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(short age) {
        this.age = age;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * Gets usertype.
     *
     * @return the usertype
     */
    public short getUsertype() {
        return usertype;
    }

    /**
     * Sets usertype.
     *
     * @param usertype the usertype
     */
    public void setUsertype(short usertype) {
        this.usertype = usertype;
    }

    /**
     * Gets org cascade id.
     *
     * @return the org cascade id
     */
    public String getOrgCascadeID() {
        return orgCascadeID;
    }

    /**
     * Sets org cascade id.
     *
     * @param orgCascadeID the org cascade id
     */
    public void setOrgCascadeID(String orgCascadeID) {
        this.orgCascadeID = orgCascadeID;
    }


}
