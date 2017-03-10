package com.realaicy.prod.jc.realglobal.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Hello controller.
 */
@RestController
@RequestMapping("/temptest")
public class TempController {

    /**
     * Index string.
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping("/todolist")
    public String todo(HttpServletRequest request) {

        String code = request.getParameter("code");   //
        String state = request.getParameter("state");   //
        System.out.println(code);

        return "todolist 我的代办事项";
    }


    @ResponseBody
    @RequestMapping("/msg")
    public String msg(HttpServletRequest request) {

        return "msg 我的消息";
    }
}