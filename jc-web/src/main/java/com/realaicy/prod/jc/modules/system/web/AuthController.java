package com.realaicy.prod.jc.modules.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @RequestMapping(value = "/system/auth/temp", method = RequestMethod.GET)
    public String initDebugPage() {
        return "system/auth/temp";
    }
}