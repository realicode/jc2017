package com.realaicy.prod.jc.modules.system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonDeletableEntity;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

/**
 * 用户安全类
 */
@SqlResultSetMapping(
        name = "preInspectionUserMapping",
        classes = {
                @ConstructorResult(
                        targetClass = PreInspectionUserVO.class,
                        columns = {
                                @ColumnResult(name = "ID"),
                                @ColumnResult(name = "NICKNAME")
                        }
                )
        }
)

@NamedNativeQuery(name = "User.getRoleUsers",
        query = "SELECT u.ID, u.NICKNAME FROM jc_sys_user_role t, jc_sys_user u "
                + "WHERE t.USERID = u.ID AND t.ROLEID = :roleID AND u.F_DELETED = 0",
        resultSetMapping = "preInspectionUserMapping")
@Entity
@Table(name = "jc_sys_user")
public class User extends CommonDeletableEntity<BigInteger> {

    /**
     * 用户名称
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 用户密码(加密后的密文)
     */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 用户昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    /**
     * 标识:各种标识
     */
    @Column(name = "ACCOUNTNONEXPIRED")
    private boolean accountNonExpired;
    @Column(name = "ACCOUNTNONLOCKED")
    private boolean accountNonLocked;
    @Column(name = "CREDENTIALSNONEXPIRED")
    private boolean credentialsNonExpired;
    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jc_sys_user_role", joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "ROLEID"))
    @JsonIgnore
    private Set<Role> roles;

    /**
     * 用户所属机构
     */
    @ManyToOne()
    @JoinColumn(name = "ORG_ID")
    private Org org;
    /**
     * 用户其他信息
     */
    @OneToOne()
    @JoinColumn(name = "USERINFO_ID")
    private UserInfo userinfo;

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
