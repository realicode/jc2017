package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.model.Menu;
import com.realaicy.prod.jc.modules.system.service.MenuService;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
public class IndexController {

    private final MenuService menuService;
    private final MyWorkService myWorkService;

    @Autowired
    public IndexController(MenuService menuService, MyWorkService myWorkService) {
        this.menuService = menuService;
        this.myWorkService = myWorkService;
    }


    @RequestMapping("/")
    public String index(@RequestParam(value = "name", required = false, defaultValue = "World V2") String name, Model model) {
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

//        myWorkService.countByUserUsername()


        return "index";
    }
}
