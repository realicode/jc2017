package com.realaicy.prod.jc.modules.pj.web;

import com.aspose.words.Document;
import com.realaicy.prod.jc.common.event.*;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.*;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALACTIONTYPE.PJ_BUILDCONTRACT_NEW;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.PJAPPLY_FINAL_DONE;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.PJAPPLY_PROVIDECONTRACT_DONE;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.PJAPPLY_PROVIDESOLUTION_DONE;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.TODOWORK.*;
import static com.realaicy.prod.jc.uitl.OtherUtil.getAsposeLicense;
import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/apply")
public class ApplianceController extends CRUDWithVOController<Appliance, BigInteger, ApplianceVO> {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final MyWorkService myWorkService;

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
    private static final String APPLY_PROVIDECONTRACT_URL = "pj/apply/providecontract";
    private static final String APPLY_PROVIDESOLUTION_URL = "pj/apply/providesolution";
    private static final String APPLY_FINAL_URL = "pj/apply/final";


    private static final String AUTH_PREFIX = "Appliance";
    private static final String AUTH_KEY_ACK = "ack";
    private static final String AUTH_KEY_APPROVE = "approve";
    private static final String AUTH_KEY_PROVIDECONTRACT = "providecontract";
    private static final String AUTH_KEY_PROVIDESOLUTION = "providesolution";
    private static final String AUTH_KEY_FINAL = "final";


    @Autowired
    public ApplianceController(ApplianceService applianceService,
                               UserService userService, ApplicationEventPublisher publisher, MyWorkService myWorkService) {
        super(applianceService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                Appliance.class, ApplianceVO.class, BINDING_WHITE_LIST);
        this.applianceService = applianceService;
        this.userService = userService;
        this.publisher = publisher;
        this.myWorkService = myWorkService;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirm(Model model,
                          @RequestParam(value = "applyid") Long applyid,
                          @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_ACK, AUTH_KEY_APPROVE)) {
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

        if (realactiontype.equals(StaticParams.REALACTIONTYPE.PJ_AFFIRM)) {
            if (result.hasFieldErrors("quotation") || result.hasFieldErrors("confirmRemark")) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }

            if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_ACK)) {
                return NO_AUTH_VIEW_NAME;
            }

            po = applianceService.findOne(realmodel.getId());
            po.setQuotation(realmodel.getQuotation());
            po.setConfirmRemark(realmodel.getConfirmRemark());
            po.setConfirmor(userService.findOne((BigInteger) httpSession.getAttribute("userid")));

            if (btnType.equals(StaticParams.BTNTYPE.DENY)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_ERR_IN_INI);
            } else if (btnType.equals(StaticParams.BTNTYPE.PASS)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_CONFIRMED);
                ApplianceConfirmEvent applianceConfirmEvent = new ApplianceConfirmEvent(realmodel.getId());
                applianceConfirmEvent.setEventKey(StaticParams.TODOWORK.APPLY_CONFIRM_KEY);
                applianceConfirmEvent.setConfirmType(btnType);
                publisher.publishEvent(applianceConfirmEvent);
            }

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
            po.setApprover(userService.findOne((BigInteger) httpSession.getAttribute("userid")));
            if (btnType.equals(StaticParams.BTNTYPE.DENY)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_ERR_IN_CONFIRM);
            } else if (btnType.equals(StaticParams.BTNTYPE.PASS)) {
                po.setStatus(StaticParams.REALSTATUS.PJAPPLY_APPROVED);
            }

            MyWork myWork = myWorkService.findByWorkUri("/pj/apply/confirm?realactiontype=approve&applyid="
                    + realmodel.getId());
            if (myWork != null) {
                //清理代办工作
                myWork.setStatus(StaticParams.REALSTATUS.MYWORK_DONE);
                myWork.setProcessDate(new Date());
                myWorkService.save(myWork);
            }

            ApplianceApproveEvent applianceApproveEvent = new ApplianceApproveEvent(realmodel.getId());
            applianceApproveEvent.setEventKey(APPLY_APPROVE_KEY);

            this.publisher.publishEvent(applianceApproveEvent);
        }

        applianceService.save(po);

        return "ok";
    }


    @RequestMapping(value = "/providecontract", method = RequestMethod.GET)
    public String provideContract(Model model,
                                  @RequestParam(value = "applyid") Long applyid,
                                  @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDECONTRACT)) {
            return NO_AUTH_VIEW_NAME;
        }
        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
        ApplyContractVO applyContractVO = new ApplyContractVO();
        applyContractVO.setApplyid(applianceVO.getId());
        model.addAttribute("realmodel", applianceVO);
        model.addAttribute("real_model_contract", applyContractVO);
        model.addAttribute("realactiontype", realactiontype);
        model.addAttribute("updateflag", "editedit");
        return APPLY_PROVIDECONTRACT_URL;
    }


    @RequestMapping(value = "/providesolution", method = RequestMethod.GET)
    public String provideSolution(Model model,
                                  @RequestParam(value = "applyid") Long applyid,
                                  @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDESOLUTION)) {
            return NO_AUTH_VIEW_NAME;
        }
        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
        TrialSolutionVO trialSolutionVO = new TrialSolutionVO();
        trialSolutionVO.setApplyid(applianceVO.getId());
        model.addAttribute("realmodel", applianceVO);
        model.addAttribute("real_model_trialsolution", trialSolutionVO);
        model.addAttribute("realactiontype", realactiontype);
        model.addAttribute("updateflag", "editedit");
        return APPLY_PROVIDESOLUTION_URL;
    }

    @RequestMapping(value = "/final", method = RequestMethod.GET)
    public String finalPage(Model model,
                            @RequestParam(value = "applyid") Long applyid,
                            @RequestParam(value = "realactiontype") String realactiontype) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_FINAL)) {
            return NO_AUTH_VIEW_NAME;
        }
        ApplianceVO applianceVO = new ApplianceVO(applianceService.findOne(BigInteger.valueOf(applyid)));
        FinalVO finalVO = new FinalVO();
        finalVO.setApplyid(applianceVO.getId());
        model.addAttribute("realmodel", applianceVO);
        model.addAttribute("real_model_final", finalVO);
        model.addAttribute("realactiontype", realactiontype);
        model.addAttribute("updateflag", "editedit");
        return APPLY_FINAL_URL;
    }

    @ResponseBody
    @RequestMapping(value = "/final", method = RequestMethod.POST)
    public String finalPost(@ModelAttribute("real_model_final") final FinalVO finalVO) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_FINAL)) {
            return NO_AUTH_STRING;
        }

        Appliance appliance = applianceService.findOne(finalVO.getApplyid());
        appliance.setFinalRemark(finalVO.getFinalRemark());
        appliance.setStatus(PJAPPLY_FINAL_DONE);
        applianceService.save(appliance);
        MyWork myWork = myWorkService.findByWorkUri(APPLY_FINAL_WORKURI
                + finalVO.getApplyid());
        if (myWork != null) {
            //清理代办工作
            myWorkService.markDone(myWork);
        }

        ApplianceFinalEvent applianceFinalEvent = new ApplianceFinalEvent();
        applianceFinalEvent.setEventKey(APPLY_FINAL_KEY);
        applianceFinalEvent.setApplyid(finalVO.getApplyid());
        this.publisher.publishEvent(applianceFinalEvent);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/buildcontract", method = RequestMethod.POST)
    public String buildcontract(@ModelAttribute("real_model_contract") final ApplyContractVO contractVO) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDECONTRACT)) {
            return NO_AUTH_STRING;
        }

        Appliance appliance = applianceService.findOne(contractVO.getApplyid());


        Map<String, Object> map = new HashMap<>();
        map.put("orgname", appliance.getOrgName());
        map.put("trialname", appliance.getName());
        map.put("trialprice", appliance.getQuotation());
        map.put("y", "2017");
        map.put("m", "3");
        map.put("d", "26");

        String filename = "realfile_" + System.currentTimeMillis();
        String fileType = ".docx";
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "G:\\Realtemp\\doc\\contract9.docx", map);

            FileOutputStream fos = new FileOutputStream(uploadfiletmppath + filename + fileType);
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!getAsposeLicense()) {
            return "";
        }
        try {
            Document doc = new Document(uploadfiletmppath + filename + fileType);
            doc.save(uploadfiletmppath + filename + ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filename + fileType;
    }


    @ResponseBody
    @RequestMapping(value = "/providecontract", method = RequestMethod.POST)
    public String provideContractPost(@ModelAttribute("real_model_contract") final ApplyContractVO contractVO) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDECONTRACT)) {
            return NO_AUTH_STRING;
        }

        Appliance appliance = applianceService.findOne(contractVO.getApplyid());
        appliance.setContractURI(contractVO.getContractURI());
        appliance.setContracttmp1(contractVO.getContracttmp1());
        appliance.setStatus(PJAPPLY_PROVIDECONTRACT_DONE);
        applianceService.save(appliance);
        MyWork myWork = myWorkService.findByWorkUri(APPLY_PROVIDECONTRACT_WORKURI
                + contractVO.getApplyid());
        if (myWork != null) {
            //清理代办工作
            myWorkService.markDone(myWork);
        }

        ApplianceProvideContractEvent applianceProvideContractEvent = new ApplianceProvideContractEvent();
        applianceProvideContractEvent.setEventKey(APPLY_PROVIDECONTRACT_KEY);
        applianceProvideContractEvent.setApplyid(contractVO.getApplyid());
        applianceProvideContractEvent.setBuildType(PJ_BUILDCONTRACT_NEW);
        this.publisher.publishEvent(applianceProvideContractEvent);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/providesolution", method = RequestMethod.POST)
    public String provideSolutionPost(@ModelAttribute("real_model_trialsolution") final TrialSolutionVO trialSolutionVO) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDESOLUTION)) {
            return NO_AUTH_STRING;
        }

        Appliance appliance = applianceService.findOne(trialSolutionVO.getApplyid());
        appliance.setContracttmp2(trialSolutionVO.getContracttmp2());
        appliance.setTrialURI(trialSolutionVO.getTrialURI());
        appliance.setStatus(PJAPPLY_PROVIDESOLUTION_DONE);
        applianceService.save(appliance);

        ApplianceProvideSolutionEvent applianceProvideSolutionEvent = new ApplianceProvideSolutionEvent();
        applianceProvideSolutionEvent.setEventKey(APPLY_PROVIDESOLUTION_KEY);
        applianceProvideSolutionEvent.setApplyid(trialSolutionVO.getApplyid());
        this.publisher.publishEvent(applianceProvideSolutionEvent);
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

        boolean tempF = false;
        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_APPROVE)) {
            model.addAttribute("real_auth_approve", "1");
            model.addAttribute("real_firstfilter", "status:2");
            tempF = true;
        }
        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_ACK)) {
            model.addAttribute("real_auth_affirm", "1");
            model.addAttribute("real_firstfilter", "status:1");
            tempF = true;
        }
        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDECONTRACT)) {
            model.addAttribute("real_auth_providecontract", "1");
            model.addAttribute("real_firstfilter", "status:1");
            tempF = true;
        }
        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PROVIDESOLUTION)) {
            model.addAttribute("real_auth_providesolution", "1");
            model.addAttribute("real_firstfilter", "status:4");
            tempF = true;
        }

        if (!tempF) {
            model.addAttribute("real_firstfilter", "status:0");
        }
    }

    @Override
    protected void extendSave(Appliance po, ApplianceVO realmodel) {
        po.setStatus(StaticParams.REALSTATUS.PJAPPLY_INI);
        po.setUser(userService.findByUsername(SpringSecurityUtil.getNameOfCurrentPrincipal()));
    }

    @Override
    protected Specification<Appliance> addSpec() {

        if (SpringSecurityUtil.hasPrivilege("superop")) {
            return null;
        }
       /* if (SpringSecurityUtil.hasAnyPrivilege(Appliance.class.getSimpleName() + "-f",
                Appliance.class.getSimpleName() + "-ack",
                Appliance.class.getSimpleName() + "-approve")) {
            return null;
        }*/
        if (SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_APPROVE, AUTH_KEY_ACK)) {
            return null;
        }
        //noinspection ConstantConditions
        return applicaitonByUserName(SpringSecurityUtil.getCurrentRealUserDetails().getUsername());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
