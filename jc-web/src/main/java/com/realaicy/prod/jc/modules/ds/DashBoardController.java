package com.realaicy.prod.jc.modules.ds;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
    @RequestMapping("/dashboard")
    public String dashboard(@RequestParam(value = "name", required = false, defaultValue = "World V2") String name, Model model) {
        model.addAttribute("name", name + "V2");
        model.addAttribute("realsign", new Date());

        return "dashboard/dashboard";
    }

    @RequestMapping("/flot")
    public String f2(@RequestParam(value = "name", required = false, defaultValue = "World V2") String name, Model model) {
        model.addAttribute("name", name + "V2");
        model.addAttribute("realsign", new Date());

        return "dashboard/flot";
    }

    @RequestMapping("/map")
    public String mapData() {

        return "dashboard/map";
    }
}
