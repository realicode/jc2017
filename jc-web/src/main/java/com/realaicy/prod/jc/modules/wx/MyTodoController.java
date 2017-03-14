package com.realaicy.prod.jc.modules.wx;

import com.realaicy.prod.jc.modules.wx.model.UserInfoResponse;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
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

        UserInfoResponse userInfo = NetUtil.getRestTemplate().getForObject(
                "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="
                        + RealCacheUtil.getWxToken() + "&code=" + code + "&agentid=2",
                UserInfoResponse.class);

        model.addAttribute("realuser", userInfo.getUserID());

        return "wx/todo";
    }
}