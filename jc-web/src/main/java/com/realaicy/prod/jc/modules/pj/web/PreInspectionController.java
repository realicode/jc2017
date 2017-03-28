package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/pre")
public class PreInspectionController {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    private static final String PRE_CONF_VIEW = "pj/pre/wizard";


    protected static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    protected static final String NO_AUTH_STRING = "NOPrivilege";

    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_PRECONF = "pre-conf";

    private final UserService userService;

    @Autowired
    public PreInspectionController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/conf", method = RequestMethod.GET)
    public String finalPage(Model model,
                            @RequestParam(value = "pjid") BigInteger pjid,
                            @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRECONF)) {
            return NO_AUTH_VIEW_NAME;
        }

        List<PreInspectionUserVO> preInspectionLeaders = userService.getUserByRole(BigInteger.valueOf(40));
        List<PreInspectionUserVO> preInspectionCheckers = userService.getUserByRole(BigInteger.valueOf(43));
        model.addAttribute("preleaders", preInspectionLeaders);
        model.addAttribute("precheckers", preInspectionCheckers);

//        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
//        FinalVO finalVO = new FinalVO();
//        finalVO.setApplyid(applianceVO.getId());
//        model.addAttribute("realmodel", applianceVO);
//        model.addAttribute("real_model_final", finalVO);
//        model.addAttribute("realactiontype", realactiontype);
//        model.addAttribute("updateflag", "editedit");
        return PRE_CONF_VIEW;
    }

}
