package com.realaicy.prod.jc.modules.wx;

import com.realaicy.prod.jc.common.event.ApplianceApproveEvent;
import com.realaicy.prod.jc.common.event.ApplianceConfirmEvent;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.model.vo.ApplianceVO;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;
import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy4Wx;

/**
 * The type Hello controller.
 */
@Controller
@RequestMapping("/wx/")
public class WxBizController {

    private final MyWorkService myWorkService;
    private final UserService userService;
    private final ApplianceService applianceService;
    private final ApplicationEventPublisher publisher;

    private static final String WX_APPLY_CONFIRM_URL = "wx/pj/apply/confirm";
    private static final String AUTH_PREFIX = "Appliance";
    private static final String AUTH_KEY_ACK = "ack";
    private static final String AUTH_KEY_APPROVE = "approve";
    private static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";

    @Autowired
    public WxBizController(MyWorkService myWorkService, UserService userService,
                           ApplianceService applianceService, ApplicationEventPublisher publisher) {
        this.myWorkService = myWorkService;
        this.userService = userService;
        this.applianceService = applianceService;
        this.publisher = publisher;
    }

    @RequestMapping(value = "/pj/apply/confirm", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "code") String code,
                           @RequestParam(value = "applyid") Long applyid,
                           @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {

        if (getUserFromCode(code) == null) {
            return "error";
        }

        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
        model.addAttribute("realmodel", applianceVO);
        model.addAttribute("realactiontype", realactiontype);
        model.addAttribute("affirmType", "");

        model.addAttribute("updateflag", "editedit");
        model.addAttribute("real_item_code", code);


        return WX_APPLY_CONFIRM_URL;
    }


    @ResponseBody
    @RequestMapping(value = "/pj/apply/confirm", method = RequestMethod.POST)
    public String doConfirm(@Valid @ModelAttribute("realmodel") final ApplianceVO realmodel,
                            final BindingResult result,
                            @RequestParam(value = "real_item_code") String code,
                            @RequestParam(value = "btnType") String btnType,
                            @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (getUserFromCode(code) == null) {
            return "error";
        }

        BigInteger userID = getUserFromCode(code);
        Appliance po = null;

        if (realactiontype.equals(StaticParams.REALACTIONTYPE.PJ_AFFIRM)) {

            if (result.hasFieldErrors("quotation") || result.hasFieldErrors("confirmRemark")) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }

            if (!hasAnyPrivilegeWithFuncByRealaicy4Wx(code, AUTH_PREFIX, AUTH_KEY_ACK)) {
                return NO_AUTH_VIEW_NAME;
            }

            //1， 改变这个业务的状态
            po = applianceService.findOne(realmodel.getId());
            po.setQuotation(realmodel.getQuotation());
            po.setConfirmRemark(realmodel.getConfirmRemark());
            po.setConfirmor(userService.findOne(userID));

            if (btnType.equals(StaticParams.BTNTYPE.DENY)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_ERR_IN_INI);
            } else if (btnType.equals(StaticParams.BTNTYPE.PASS)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_CONFIRMED);

                ApplianceConfirmEvent applianceConfirmEvent = new ApplianceConfirmEvent(realmodel.getId());
                applianceConfirmEvent.setEventKey(StaticParams.TODOWORK.APPLY_CONFIRM_KEY);
                applianceConfirmEvent.setConfirmType(btnType);
                this.publisher.publishEvent(applianceConfirmEvent);
            }

            //2， 如果是待办事项，则清理待办工作
            MyWork myWork = myWorkService.findByWorkUri("/pj/apply/confirm?realactiontype=affirm&applyid="
                    + realmodel.getId());
            if (myWork != null) {
                //清理代办工作
                myWorkService.markDone(myWork);
            }

        } else if (realactiontype.equals(StaticParams.REALACTIONTYPE.PJ_APPROVE)) {
            if (result.hasFieldErrors("approveRemark")) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }

            if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_APPROVE)) {
                return NO_AUTH_VIEW_NAME;
            }
            po = applianceService.findOne(realmodel.getId());
            po.setApproveRemark(realmodel.getApproveRemark());
            po.setApprover(userService.findOne(userID));
            if (btnType.equals(StaticParams.BTNTYPE.DENY)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_ERR_IN_CONFIRM);
            } else if (btnType.equals(StaticParams.BTNTYPE.PASS)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_APPROVED);

                ApplianceApproveEvent applianceApproveEvent = new ApplianceApproveEvent(realmodel.getId());
                applianceApproveEvent.setEventKey(StaticParams.TODOWORK.APPLY_APPROVE_KEY);
                this.publisher.publishEvent(applianceApproveEvent);
            }

            MyWork myWork = myWorkService.findByWorkUri("/pj/apply/confirm?realactiontype=approve&applyid="
                    + realmodel.getId());
            if (myWork != null) {
                //清理代办工作
                myWorkService.markDone(myWork);
            }

        }

        applianceService.save(po);

        return "ok";
    }


    @ResponseBody
    @RequestMapping(value = "/me/work/count", method = RequestMethod.GET)
    public Long getMyWorkCount(@RequestParam(value = "code") String code) {

        if (getUserFromCode(code) == null) {
            return 0L;
        }

        BigInteger userID = getUserFromCode(code);
        return myWorkService.todoWorkCountByUserId(getUserFromCode(code));
    }

    private BigInteger getUserFromCode(String code) {
        return RealCacheUtil.CORE_USER.getOrDefault(code, null);
    }

}