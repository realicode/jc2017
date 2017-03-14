package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.pj.model.Application;
import com.realaicy.prod.jc.modules.pj.model.ApplicationVO;
import com.realaicy.prod.jc.modules.pj.service.ApplicationService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.modules.wx.model.msg.RealMsgContent;
import com.realaicy.prod.jc.modules.wx.model.msg.RealRes;
import com.realaicy.prod.jc.modules.wx.model.msg.Realmsg;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/pj/apply")
public class ApplicationController extends CRUDWithVOController<Application, BigInteger, ApplicationVO> {


    private final UserService userService;

    private static Specification<Application> applicaitonByUserName(String userName) {
        return (userRoot, query, cb) -> cb.equal(userRoot.get("user").get("username"), userName);
    }

    private ApplicationService applicationService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "pj/apply/page";
    private static final String NEW_ENTITY_URL = "pj/apply/add";
    private static final String EDIT_ENTITY_URL = "pj/apply/add";
    private static final String LIST_ENTITY_URL = "pj/apply/page";
    private static final String SEARCH_ENTITY_URL = "pj/apply/search";
    private static final String APPLY_CONFIRM_URL = "pj/apply/confirm";


    @Autowired
    public ApplicationController(ApplicationService applicationService, UserService userService) {
        super(applicationService, "application", NAME_DIC, PAGE_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, Application.class, ApplicationVO.class, BINDING_WHITE_LIST);
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "applyid") Long applyid) throws InstantiationException {

        if (!checkAuth("ack", Application.class.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        ApplicationVO applicationVO = new ApplicationVO(applicationService.findOne(BigInteger.valueOf(applyid)));
        model.addAttribute("realmodel", applicationVO);
        model.addAttribute("realactiontype", "confirm");
        model.addAttribute("applyid", applicationVO.getId());
        model.addAttribute("confirmType", "");

        model.addAttribute("updateflag", "editedit");


        return APPLY_CONFIRM_URL;
    }

    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String doConfirm(@Valid @ModelAttribute("realmodel") final ApplicationVO realmodel,
                            final BindingResult result,
                            @RequestParam(value = "applyid") Long applyid,
                            @RequestParam(value = "confirmType") String confirmType) throws InstantiationException {

        if (result.hasFieldErrors("quotation") || result.hasFieldErrors("confirmRemark")) {
            return "error绑定异常（非页面提交，你是机器人？）";
        }

        if (!checkAuth("ack", Application.class.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        Application po = applicationService.findOne(BigInteger.valueOf(applyid));
        po.setQuotation(realmodel.getQuotation());
        po.setConfirmRemark(realmodel.getConfirmRemark());

        if (confirmType.equals("nopass")) {
            po.setStatus(Short.valueOf("-1"));
        } else if (confirmType.equals("pass")) {
            po.setStatus(Short.valueOf("2"));
        }

        applicationService.save(po);

        return "ok";
    }


    @Override
    protected List<ApplicationVO> convertFromPOListToVOList(List<Application> poList) {
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        ApplicationVO applicationVOTemp;
        for (Application po : poList) {
            applicationVOTemp = new ApplicationVO(po);
            applicationVOList.add(applicationVOTemp);
        }
        return applicationVOList;
    }

    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected boolean canBeDelete(Application entity) {
        return false;
    }

    @Override
    protected void saveNewEntityCallBack() {
        //通知秘书有新申请
        Realmsg realms = new Realmsg();
        RealMsgContent content = new RealMsgContent();
        content.setContent("尊敬的秘书长：有一个新的稽查项目申请，请登录系统查看");

        realms.setAgentid(2);
        realms.setMsgtype("text");
        realms.setTouser(StaticParams.WX.NEWAPPLYSENTTO);
        realms.setText(content);


        RealRes realRes = NetUtil.getRestTemplate().postForObject("https://qyapi.weixin.qq.com/cgi-bin/message/send?"
                        + "access_token=" + RealCacheUtil.getWxToken(),
                realms, RealRes.class);
        System.out.println(realRes.toString());
    }

    @Override
    protected void internalSaveNew(ApplicationVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
    }

    @Override
    protected Application internalSaveUpdate(ApplicationVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        return null;
    }

    @Override
    protected void addAttrToModel(Model model) {
        if (SpringSecurityUtil.hasPrivilege(this.getClass().getSimpleName() + "-approve")) {
            model.addAttribute("real_auth_approve", 1);
        }

    }

    @Override
    protected void extendSave(Application po, BigInteger updateID, BigInteger pid) {
        po.setStatus(Short.valueOf("1"));
        po.setUser(userService.findByName(SpringSecurityUtil.getNameOfCurrentPrincipal()));
    }

    @Override
    protected Specification<Application> addSpec() {
        if (SpringSecurityUtil.hasPrivilege(Application.class.getSimpleName() + "-ack")) {
            return null;
        }

        return applicaitonByUserName(SpringSecurityUtil.getCurrentRealUserDetails().getUsername());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
