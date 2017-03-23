package com.realaicy.prod.jc.modules.wx;

import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import com.realaicy.prod.jc.realglobal.security.RealUserDetailsService;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * The type Hello controller.
 */
@Controller
@RequestMapping("/wx/todo")
public class MyTodoController {

    private static Logger logger = LoggerFactory.getLogger(MyTodoController.class);

    private final MyWorkService myWorkService;
    private final UserService userService;
    private final RealUserDetailsService realUserDetailsService;

    @Autowired
    public MyTodoController(MyWorkService myWorkService, UserService userService, RealUserDetailsService realUserDetailsService) {
        this.myWorkService = myWorkService;
        this.userService = userService;
        this.realUserDetailsService = realUserDetailsService;
    }

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

//        String wxID = WxUtil.getUserInfoFromCode(code).getUserID(); //wx userid
//        BigInteger userID = userService.findByUserinfoWxuserid(wxID).getId(); // system userid
//        String nickname = userService.findByUserinfoWxuserid(wxID).getNickname();
//        String username = userService.findByUserinfoWxuserid(wxID).getUsername();
        String wxID = "XudongLiu";
        code = "1111";
        logger.info("wxID: {}", wxID);

        /*odel.addAttribute("realuser", "阎昭");
        List<MyWork> todolist = myWorkService.findByUserUsername("zhaoy");*/

        model.addAttribute("realuser", "王雨萌");
        List<MyWork> todolist = myWorkService.findTodoWorkByUserUsername("wym");

        model.addAttribute("todolist", todolist);
        model.addAttribute("myworkcount", todolist.size());

        model.addAttribute("code", code);

//        RealCacheUtil.CORE_USER.put(code, userID);
        RealCacheUtil.CORE_USER.put(code, BigInteger.valueOf(116));
        RealCacheUtil.CORE_USERSECURITYDETAILS.put(code, (RealUserDetails) realUserDetailsService.loadUserByUsername("wym"));


        return "wx/todo";
    }


    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/todolist2")
    public String todo2(HttpServletRequest request, Model model) {

        return "wx/fui/index";
    }

    @RequestMapping("/todoitem")
    public String todoItem(@RequestParam(value = "itemid") String itemid,
                           HttpServletRequest request, Model model) {

        System.out.println("itemid：" + itemid);
        return "wx/todoitem";
    }
}