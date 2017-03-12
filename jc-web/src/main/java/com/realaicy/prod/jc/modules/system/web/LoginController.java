package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import com.realaicy.prod.jc.modules.system.model.vo.UserRegisVO;
import com.realaicy.prod.jc.modules.system.repos.UserSecRepos;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
public class LoginController {


    private final PasswordEncoder bcryptEncoder;
    private UserService userService;
    private OrgService orgService;
    private final UserSecRepos userSecRepos;


    @Autowired
    public LoginController(PasswordEncoder bcryptEncoder, OrgService orgService, UserService userService, UserSecRepos userSecRepos) {
        this.bcryptEncoder = bcryptEncoder;
        this.orgService = orgService;
        this.userService = userService;
        this.userSecRepos = userSecRepos;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String initSignUpPage() {
        return "signup";
    }

    @ResponseBody
    @RequestMapping(value = "/regisuser", method = RequestMethod.POST)
    public String doReg(@Valid @ModelAttribute("realmodel") final UserRegisVO realmodel, HttpServletRequest request,
                        final BindingResult result) {
        if (result.hasErrors()) {
            return "error绑定异常（非页面提交，你是机器人？）";
        }

        String clientIP = NetUtil.getClientIpAddress(request);

        if (RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.contains(clientIP, "regisuser")
                && RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.get(clientIP, "regisuser") >= StaticParams.FUNCMAX.REGISUSER) {
            return "Error:重试次数过多";
        }
        if (!RealCacheUtil.SMS_CODE_MAP.containsRow(realmodel.getMobile())
                || !RealCacheUtil.SMS_CODE_MAP.contains(realmodel.getMobile(), realmodel.getMobilecode())
                || !RealCacheUtil.SMS_CODE_MAP.get(realmodel.getMobile(),
                realmodel.getMobilecode()).isAfter(LocalDateTime.now().minusMinutes(10L))) { //验证码不正确

            realSecurity(clientIP);
            return "Error:手机验证码错误";
        }

        if (userService.findByName(realmodel.getUsername()) != null) {
            realSecurity(clientIP);
            return "Error:用户名已经存在";
        }

        realmodel.setPassword(bcryptEncoder.encode(realmodel.getPassword()));

        UserSec userSec = new UserSec();
        User user = new User();
        User user2 = new User();

        try {
            BeanUtils.copyProperties(userSec, realmodel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return "Error:内部错误";
        }
        userSec.setAccountNonExpired(true);
        userSec.setCredentialsNonExpired(true);
        userSec.setAccountNonLocked(true);
        userSec.setEnabled(true);
        userSec.setCreaterID(BigInteger.valueOf(6L));
        userSec.setUpdaterID(BigInteger.valueOf(6L));
        userSec.setCreateTime(new Date());
        userSec.setUpdateTime(new Date());
        userSec.setNickname(realmodel.getUsername());

        userSecRepos.save(userSec);

        user.setUsername(realmodel.getUsername());
        user.setNickname(realmodel.getUsername());
        user.setPassword(realmodel.getPassword());
        user.setEmail(realmodel.getEmail());
        user.setMobile(realmodel.getMobile());
        user.setCreaterID(BigInteger.valueOf(6L));
        user.setUpdaterID(BigInteger.valueOf(6L));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        user.setOrg(orgService.findOne(BigInteger.valueOf(117L)));
        user.setUsertype(Short.valueOf("5"));
        userService.update(user);

        return "ok";
    }

    private void realSecurity(String clientIP) {
        if (RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.contains(clientIP, "regisuser")) {
            RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.put(clientIP, "regisuser",
                    RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.get(clientIP, "regisuser") + 1);
        } else {
            RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.put(clientIP, "regisuser", 1);
        }
    }

}