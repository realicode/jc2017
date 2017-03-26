package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.model.Menu;
import com.realaicy.prod.jc.modules.system.model.RealLog;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.UserInfo;
import com.realaicy.prod.jc.modules.system.repos.LogRepos;
import com.realaicy.prod.jc.modules.system.service.MenuService;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.SESSIONKEY.USERNAME;

/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
public class IndexController {

    private final MenuService menuService;
    private final UserService userService;
    private final UserInfoService userInfoService;
    private final MyWorkService myWorkService;
    private final LogRepos logRepos;

    @Autowired
    public IndexController(MenuService menuService, UserService userService,
                           UserInfoService userInfoService, MyWorkService myWorkService, LogRepos logRepos) {
        this.menuService = menuService;
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.myWorkService = myWorkService;
        this.logRepos = logRepos;
    }


    @RequestMapping("/")
    public String index(@RequestParam(value = "name", required = false) String name,
                        Model model, HttpSession httpSession) {
        model.addAttribute("name", name + "V2");
        model.addAttribute("realsign", new Date());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
        String landing = LocalDateTime.now().format(format);

        model.addAttribute("lastNotificationTime", "最后更新时间: " + landing);
        model.addAttribute("usernickname",
                ((RealUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        List<Menu> menuList;

        menuList = menuService.findUserMenu();
        model.addAttribute("realmenus", menuList);

        httpSession.setAttribute(StaticParams.SESSIONKEY.USERID,
                ((RealUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        httpSession.setAttribute(USERNAME,
                ((RealUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        String myworkcount = myWorkService.todoWorkCountByUserId(
                (BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID)).toString();
        model.addAttribute("myworkcount", myworkcount);

        User user = userService.findByUsername((String) httpSession.getAttribute(USERNAME));
        String purl = user.getUserinfo().getPortraitUrl();
        if (purl == null) {
            purl = "lxd.jpg";
        }
        model.addAttribute("portraitUrl", purl);


        RealLog realLog = new RealLog();
        realLog.setLogType("login");
        realLog.setLogSubject(user);
        realLog.setLogTime(new Date());
        realLog.setLogClient("system");
        realLog.setLogModule("system");
        realLog.setLogAction("login");
        logRepos.save(realLog);

        UserInfo userInfo = user.getUserinfo();
        userInfo.setLastLogin(realLog.getLogTime());
        userInfoService.save(userInfo);

        return "index";
    }
}
