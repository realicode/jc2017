package com.realaicy.prod.jc.modules.wx;

import com.realaicy.prod.jc.uitl.WxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Hello controller.
 */
@Controller
@RequestMapping("/wx/todo")
public class MyTodoController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/todolist")
    public String todo(HttpServletRequest request, Model model) {

        String code = request.getParameter("code");   //
//        String state = request.getParameter("state");   //
//        System.out.println(code);


        model.addAttribute("realuser", WxUtil.getUserInfoFromCode(code).getUserID());

        return "wx/todo";
    }
}