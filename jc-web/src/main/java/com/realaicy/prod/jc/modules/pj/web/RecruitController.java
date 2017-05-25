package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import com.realaicy.prod.jc.modules.pj.model.vo.RecruitSupplyVO;
import com.realaicy.prod.jc.modules.pj.service.RecruitSupplyService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/recruit")
public class RecruitController extends CRUDWithVOController<RecruitSupply, BigInteger, RecruitSupplyVO> {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final MyWorkService myWorkService;

    private RecruitSupplyService recruitSupplyService;

    private static final String[] NAME_DIC = {"projectFacadeName"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "pj/recruit/page";
    private static final String SHOW_ENTITY_URL = "pj/recruit/detail";
    private static final String NEW_ENTITY_URL = "pj/recruit/add";
    private static final String EDIT_ENTITY_URL = "pj/recruit/add";
    private static final String LIST_ENTITY_URL = "pj/recruit/page";
    private static final String SEARCH_ENTITY_URL = "pj/recruit/search";

    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_PRESTART = "pre-start";
    private static final String AUTH_KEY_RECRUIT = "pre-recruit";
    private static final String AUTH_KEY_START = "start";

    private static final String AUTH_KEY_APPROVE = "approve";


    @Autowired
    public RecruitController(RecruitSupplyService recruitSupplyService,
                             UserService userService, ApplicationEventPublisher publisher, MyWorkService myWorkService) {
        super(recruitSupplyService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                RecruitSupply.class, RecruitSupplyVO.class, BINDING_WHITE_LIST);
        this.recruitSupplyService = recruitSupplyService;
        this.userService = userService;
        this.publisher = publisher;
        this.myWorkService = myWorkService;
    }

    @ResponseBody
    @RequestMapping(value = "/accept/{supplyid}", method = RequestMethod.GET)
    public String acceptRecurit(@PathVariable("supplyid") BigInteger supplyid, HttpSession httpSession) {

        RecruitSupply recruitSupply = recruitSupplyService.findOne(supplyid);
        recruitSupply.getApplys().add(
                userService.findOne((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID)));

        recruitSupplyService.save(recruitSupply);
        System.out.println();

        return "ok";
    }

    @Override
    protected void addAttrToModel(Model model) {

        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRESTART)) {
            model.addAttribute("auth_pj_pre_start", "1");
        }

        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_RECRUIT)) {
            model.addAttribute("auth_pj_pre_recruit", "1");
        }

    }


    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected List<RecruitSupplyVO> convertFromPOListToVOList(List<RecruitSupply> poList) {
        List<RecruitSupplyVO> recruitSupplyVOList = new ArrayList<>();
        RecruitSupplyVO recruitSupplyVOTemp;
        for (RecruitSupply po : poList) {
            recruitSupplyVOTemp = new RecruitSupplyVO(po);
            recruitSupplyVOList.add(recruitSupplyVOTemp);
        }
        return recruitSupplyVOList;
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return false;
    }

    @Override
    protected void checkBeforeSaveNew(RecruitSupplyVO realmodel) throws SaveNewException {

    }


    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
