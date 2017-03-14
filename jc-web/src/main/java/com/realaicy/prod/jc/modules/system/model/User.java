package com.realaicy.prod.jc.modules.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 文档实体类
 */
@Entity
@Table(name = "jc_sys_user")
public class User extends CommonDeletableEntity<BigInteger> {


    /**
     * 用户名称
     */
    @Column(name = "USERNAME")
    @NotEmpty
    @Size(min = 5, max = 20)
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
     * 用户所属机构
     */
    @ManyToOne()
    @JoinColumn(name = "ORG_ID")
    private Org org;

    /**
     * 用户角色
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jc_sys_user_role", joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "ROLEID"))
    @JsonIgnore
    private Set<Role> roles;

    /**
     * 用户代办事项列表
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<MyWork> workList = new LinkedList<>();

    /**
     * 用户角色名字
     */
    @Column(name = "ROLENAMES")
    private String rolenames;

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
