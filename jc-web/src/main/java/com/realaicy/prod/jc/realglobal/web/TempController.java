package com.realaicy.prod.jc.realglobal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Hello controller.
 */
@Controller
@RequestMapping("/temptest")
public class TempController {



    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/xc")
    public String xingcheng(HttpServletRequest request, Model model) {

        String code = request.getParameter("code");   //
        String state = request.getParameter("state");   //
        System.out.println(code);
        model.addAttribute("realcode", code);

        return "wx/todo";
    }

    @ResponseBody
    @RequestMapping("/msg")
    public String msg(HttpServletRequest request) {

        return "msg 我的消息";
    }
}