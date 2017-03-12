package com.realaicy.prod.jc.modules.system.model.vo;

import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/**
 * Created by realaicy on 2016/10/16.
 * <p>
 * xxs
 */
public class UserRegisVO extends BaseVO<BigInteger> {
    private BigInteger id;
    /**
     * 用户名称
     */
    @NotEmpty
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-zA-Z]{1}([a-zA-Z0-9]){4,19}$",message = "用户名格式不正确")
    private String username;

    /**
     * 用户密码(加密后的密文)
     */
    @NotEmpty
    @Pattern(regexp = "([a-zA-Z0-9]){8,20}$")
    private String password;

    /**
     * 用户确认密码
     */
    @NotEmpty
    private String passwdconfirm;
    /**
     * 用户手机验证码
     */
    @NotEmpty
    @Pattern(regexp = "([0-9]){6,6}$")
    private String mobilecode;
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

    public UserRegisVO() {
    }

    public String getPasswdconfirm() {
        return passwdconfirm;
    }

    public void setPasswdconfirm(String passwdconfirm) {
        this.passwdconfirm = passwdconfirm;
    }

    public String getMobilecode() {
        return mobilecode;
    }

    public void setMobilecode(String mobilecode) {
        this.mobilecode = mobilecode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
