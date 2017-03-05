package com.realaicy.prod.jc.modules.system.web;

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

    @Autowired
    public IndexController(MenuService menuService) {
        this.menuService = menuService;
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


        //UserDetails userDetails = SpringSecurityUtil.getCurrentUserDetails();

        List<Menu> menuList;
        /*Menu menu = new Menu();
        menu.setName("动态" + LocalTime.now());
        menuList.add(menu);
        Menu menu2 = new Menu();
        for (int i = 1; i < 100; i++) ;

        menu2.setName("动态" + LocalTime.now());
        menuList.add(menu2);*/
        // menuList = menuRepos.findAllMenus();
        menuList = menuService.findUserMenu();
        model.addAttribute("realmenus", menuList);
        return "index";
    }
}
