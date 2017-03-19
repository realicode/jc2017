package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.event.ApplianceCreatedEvent;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.model.ApplianceVO;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/apply")
public class ApplianceController extends CRUDWithVOController<Appliance, BigInteger, ApplianceVO> {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    private static Specification<Appliance> applicaitonByUserName(String userName) {
        return (userRoot, query, cb) -> cb.equal(userRoot.get("user").get("username"), userName);
    }

    private ApplianceService applianceService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "pj/apply/page";
    private static final String SHOW_ENTITY_URL = "pj/apply/detail";
    private static final String NEW_ENTITY_URL = "pj/apply/add";
    private static final String EDIT_ENTITY_URL = "pj/apply/add";
    private static final String LIST_ENTITY_URL = "pj/apply/page";
    private static final String SEARCH_ENTITY_URL = "pj/apply/search";
    private static final String APPLY_CONFIRM_URL = "pj/apply/confirm";

    private static final String AUTH_PREFIX = "Appliance";


    @Autowired
    public ApplianceController(ApplianceService applianceService,
                               UserService userService, ApplicationEventPublisher publisher) {
        super(applianceService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                Appliance.class, ApplianceVO.class, BINDING_WHITE_LIST);
        this.applianceService = applianceService;
        this.userService = userService;
        this.publisher = publisher;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "applyid") Long applyid,
                           @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, "ack", "approve")) {
            return NO_AUTH_VIEW_NAME;
        }
        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
        model.addAttribute("realmodel", applianceVO);
        model.addAttribute("realactiontype", realactiontype);
        model.addAttribute("affirmType", "");

        model.addAttribute("updateflag", "editedit");

        return APPLY_CONFIRM_URL;
    }

    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String doConfirm(@Valid @ModelAttribute("realmodel") final ApplianceVO realmodel,
                            final BindingResult result,
                            HttpSession httpSession,
                            @RequestParam(value = "btnType") String btnType,
                            @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {
        Appliance po = null;

        if (realactiontype.equals("affirm")) {
            if (result.hasFieldErrors("quotation") || result.hasFieldErrors("confirmRemark")) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }

            if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, "ack")) {
                return NO_AUTH_VIEW_NAME;
            }

            po = applianceService.findOne(realmodel.getId());
            po.setQuotation(realmodel.getQuotation());
            po.setConfirmRemark(realmodel.getConfirmRemark());
            po.setConfirmor(userService.findOne((BigInteger) httpSession.getAttribute("userid")));

            if (btnType.equals("nopass")) {
                po.setStatus(Short.valueOf("4101"));
            } else if (btnType.equals("pass")) {
                po.setStatus(Short.valueOf("2"));
            }

        } else if (realactiontype.equals("approve")) {
            if (result.hasFieldErrors("approveRemark")) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }

            if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, "approve")) {
                return NO_AUTH_VIEW_NAME;
            }
            po = applianceService.findOne(realmodel.getId());
            po.setApproveRemark(realmodel.getApproveRemark());
            po.setApprover(userService.findOne((BigInteger) httpSession.getAttribute("userid")));
            if (btnType.equals("nopass")) {
                po.setStatus(Short.valueOf("4201"));
            } else if (btnType.equals("pass")) {
                po.setStatus(Short.valueOf("3"));
            }
        }

        applianceService.save(po);

        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String doApprove(@Valid @ModelAttribute("realmodel") final ApplianceVO realmodel,
                            final BindingResult result,
                            HttpSession httpSession,
                            @RequestParam(value = "approveType") String btnType) throws InstantiationException {

        if (result.hasFieldErrors("approveRemark")) {
            return "error绑定异常（非页面提交，你是机器人？）";
        }

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, "approve")) {
            return NO_AUTH_VIEW_NAME;
        }

        Appliance po = applianceService.findOne(realmodel.getId());

        po.setApproveRemark(realmodel.getConfirmRemark());
        po.setApprover(userService.findOne((BigInteger) httpSession.getAttribute("userid")));

        if (btnType.equals("nopass")) {
            po.setStatus(Short.valueOf("4201"));
        } else if (btnType.equals("pass")) {
            po.setStatus(Short.valueOf("3"));
        }

        applianceService.save(po);

        return "ok";
    }


    @Override
    protected List<ApplianceVO> convertFromPOListToVOList(List<Appliance> poList) {
        List<ApplianceVO> applianceVOList = new ArrayList<>();
        ApplianceVO applianceVOTemp;
        for (Appliance po : poList) {
            applianceVOTemp = new ApplianceVO(po);
            applianceVOList.add(applianceVOTemp);
        }
        return applianceVOList;
    }

    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return false;
    }

    @Override
    protected void saveNewEntityCallBack(BigInteger id) {

        ApplianceCreatedEvent applianceCreatedEvent = new ApplianceCreatedEvent(id);
        applianceCreatedEvent.setEventKey("Appliance_Creation");
        this.publisher.publishEvent(applianceCreatedEvent);
    }

    @Override
    protected void checkBeforeSaveNew(ApplianceVO realmodel) throws SaveNewException {
    }

    @Override
    protected void extendShowDetail(Appliance po, ApplianceVO vo) {
        if (po.getConfirmor() != null) {
            vo.setConfirmorName(po.getConfirmor().getNickname());
        }
        if (po.getApprover() != null) {
            vo.setApproverName(po.getApprover().getNickname());
        }
    }

    @Override
    protected void addAttrToModel(Model model) {
        if (SpringSecurityUtil.hasPrivilege(Appliance.class.getSimpleName() + "-approve")) {
            model.addAttribute("real_auth_approve", "1");
            model.addAttribute("real_firstfilter", "status:2");

        } else if (SpringSecurityUtil.hasPrivilege(Appliance.class.getSimpleName() + "-ack")) {
            model.addAttribute("real_auth_affirm", "1");
            model.addAttribute("real_firstfilter", "status:1");

        }
    }

    @Override
    protected void extendSave(Appliance po, ApplianceVO realmodel) {
        po.setStatus(Short.valueOf("1"));
        po.setUser(userService.findByUsername(SpringSecurityUtil.getNameOfCurrentPrincipal()));
    }

    @Override
    protected Specification<Appliance> addSpec() {

        if (SpringSecurityUtil.hasPrivilege("superop")) {
            return null;
        }
        if (SpringSecurityUtil.hasAnyPrivilege(Appliance.class.getSimpleName() + "-f",
                Appliance.class.getSimpleName() + "-ack",
                Appliance.class.getSimpleName() + "-approve")) {
            return null;
        }
        return applicaitonByUserName(SpringSecurityUtil.getCurrentRealUserDetails().getUsername());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
