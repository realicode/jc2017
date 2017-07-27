package com.realaicy.prod.jc.modules.pj.web;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.realaicy.prod.jc.common.event.PJPreCheckItemDoneEvent;
import com.realaicy.prod.jc.common.event.PJPreConfEvent;
import com.realaicy.prod.jc.common.event.PreRecruitPubEvent;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItem;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItemRunTime;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckRunTime;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckModuleVO;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRunTimeRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckModuleRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckRunTimeRepos;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckItemService;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckModuleService;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckRunTimeService;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import com.realaicy.prod.jc.modules.pj.model.vo.*;
import com.realaicy.prod.jc.modules.pj.service.PreInspectionService;
import com.realaicy.prod.jc.modules.pj.service.ProjectFacadeService;
import com.realaicy.prod.jc.modules.pj.service.RecruitSupplyService;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.CHECKITEM_DONE;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALSTATUS.PJPRE_RECRUIT_START;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.TODOWORK.PJ_PRE_CHECKITEM_DO_WORKURI;
import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/pre")
public class PreInspectionController {

    private static final String PRE_CONF_VIEW = "pj/pre/wizard";
    private static final String PRE_DO_VIEW = "pj/pre/docheck";
    private static final String PRE_RECRUIT_VIEW = "pj/pre/recruit";
    private static final String PRE_DETAIL_VIEW = "pj/pre/detail";
    private static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    private static final String NO_AUTH_STRING = "NOPrivilege";
    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_RECRUIT = "pre-recruit";
    private static final String AUTH_KEY_PRESTART = "pre-start";
    private static final String AUTH_KEY_PRE_DO = "pre-check-do";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private final UserService userService;
    private final ProjectFacadeService facadeService;
    private final PreInspectionService preInspectionService;
    private final RecruitSupplyService recruitSupplyService;
    private final PreCheckModuleRepos preCheckModuleRepos;
    private final PreCheckRunTimeRepos preCheckRunTimeRepos;
    private final PreCheckRunTimeService preCheckRunTimeService;
    private final PreCheckItemRunTimeRepos preCheckItemRunTimeRepos;
    private final PreCheckItemService preCheckItemService;
    private final ApplicationEventPublisher publisher;
    private final MyWorkService myWorkService;
    private final PreCheckModuleService preCheckModuleService;
    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;


    @Autowired
    public PreInspectionController(UserService userService, ProjectFacadeService facadeService,
                                   PreInspectionService preInspectionService,
                                   RecruitSupplyService recruitSupplyService,
                                   PreCheckModuleRepos preCheckModuleRepos,
                                   PreCheckRunTimeRepos preCheckRunTimeRepos,
                                   PreCheckRunTimeService preCheckRunTimeService, PreCheckItemRunTimeRepos preCheckItemRunTimeRepos,
                                   PreCheckItemService preCheckItemService, ApplicationEventPublisher publisher,
                                   MyWorkService myWorkService, PreCheckModuleService preCheckModuleService) {
        this.userService = userService;
        this.facadeService = facadeService;
        this.preInspectionService = preInspectionService;
        this.recruitSupplyService = recruitSupplyService;
        this.preCheckModuleRepos = preCheckModuleRepos;
        this.preCheckRunTimeRepos = preCheckRunTimeRepos;
        this.preCheckRunTimeService = preCheckRunTimeService;
        this.preCheckItemRunTimeRepos = preCheckItemRunTimeRepos;
        this.preCheckItemService = preCheckItemService;
        this.publisher = publisher;
        this.myWorkService = myWorkService;
        this.preCheckModuleService = preCheckModuleService;
    }


    @RequestMapping(value = "/recruit", method = RequestMethod.GET)
    public String renderRecruit(Model model,
                                @RequestParam(value = "pjid") BigInteger pjid) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_RECRUIT)) {
            return NO_AUTH_VIEW_NAME;
        }

        RecruitSupplyVO recruitSupplyVO = new RecruitSupplyVO();
        recruitSupplyVO.setWorkType(StaticParams.RECRUITTYPE.PRECHECK);
        recruitSupplyVO.setProjectFacadeID(pjid);
        model.addAttribute("realmodel", recruitSupplyVO);

        return PRE_RECRUIT_VIEW;
    }

    @ResponseBody
    @RequestMapping(value = "/pubrecruit", method = RequestMethod.POST)
    public String pubRecruit(RecruitSupplyVO model, HttpSession httpSession) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_RECRUIT)) {
            return NO_AUTH_VIEW_NAME;
        }

        RecruitSupply recruitSupply = new RecruitSupply();
        ProjectFacade projectFacade = facadeService.findOne(model.getProjectFacadeID());

        recruitSupply.setComment(model.getComment());
        recruitSupply.setDeadLine(model.getDeadLine());
        recruitSupply.setEstStartDate(model.getEstStartDate());
        recruitSupply.setEstEndDate(model.getEstEndDate());
        recruitSupply.setWorkType(StaticParams.RECRUITTYPE.PRECHECK);
        recruitSupply.setProjectFacade(projectFacade);
        recruitSupply.setCreaterID((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
        recruitSupply.setCreateTime(new Date());
        recruitSupply.setUpdaterID(recruitSupply.getCreaterID());
        recruitSupply.setUpdateTime(recruitSupply.getCreateTime());
        recruitSupply.setDeleteFlag(false);
        recruitSupplyService.save(recruitSupply);

        projectFacade.setPubPreStatus(PJPRE_RECRUIT_START);
        facadeService.save(projectFacade);

        PreRecruitPubEvent preRecruitPubEvent = new PreRecruitPubEvent();
        preRecruitPubEvent.setEventKey(StaticParams.EVENTKEY.PJ_PRE_RECRUIT_KEY);
        preRecruitPubEvent.setPjid(projectFacade.getId());
        publisher.publishEvent(preRecruitPubEvent);


        System.out.println();

        return StaticParams.RETURNMSG.SUCCESS;
    }

    @RequestMapping(value = "/conf", method = RequestMethod.GET)
    public String finalPage(Model model,
                            @RequestParam(value = "pjid") BigInteger pjid) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRESTART)) {
            return NO_AUTH_VIEW_NAME;
        }

        HashSet<PreInspectionUserVO> preInspectionLeaders = userService.getUserByRole(BigInteger.valueOf(40));
        HashSet<PreInspectionUserVO> preInspectionCheckers = userService.getUserByRole(BigInteger.valueOf(43));

        HashSet<PreInspectionUserVO> preInspectionApplyCheckers = new HashSet<>();
        HashSet<PreInspectionUserVO> preInspectionOtherCheckers;

        List<User> users = recruitSupplyService.findApplysByPJID(pjid);

        for (User user : users) {
            PreInspectionUserVO preInspectionUserVO = new PreInspectionUserVO(user.getId(), user.getNickname());
            preInspectionApplyCheckers.add(preInspectionUserVO);
        }

        preInspectionCheckers.removeAll(preInspectionApplyCheckers);
        preInspectionOtherCheckers = preInspectionCheckers;

        List<String> allTems = preCheckModuleService.getTemNameList();
        model.addAttribute("pretems", allTems);


        PreInspectionVO preInspectionVO = new PreInspectionVO();
        preInspectionVO.setPjID(pjid);

        model.addAttribute("preleaders", preInspectionLeaders);
        model.addAttribute("precheckers", preInspectionCheckers);
        model.addAttribute("preapplycheckers", preInspectionApplyCheckers);
        model.addAttribute("preothercheckers", preInspectionOtherCheckers);

        model.addAttribute("realmodel", preInspectionVO);

        return PRE_CONF_VIEW;
    }


    @RequestMapping(value = "/docheck", method = RequestMethod.GET)
    public String iniCheckPage(Model model, @RequestParam(value = "checkmoduleruntimeid") BigInteger checkmoduleruntimeid) {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRE_DO)) {
            return NO_AUTH_VIEW_NAME;
        }


        PreCheckRunTime preCheckRunTime = preCheckRunTimeRepos.findOne(checkmoduleruntimeid);
        PreInspection preInspection = preCheckRunTime.getPreInspection();
        List<PreCheckItem> preCheckItems = preCheckItemService.findByModuleID(preCheckRunTime.getCheckModule().getId());


        PreInspectionShowVO preInspectionShowVO = new PreInspectionShowVO(preInspection);

        PreInspectionCheckRuntimeVO preInspectionCheckRuntimeVO = new PreInspectionCheckRuntimeVO();
        preInspectionCheckRuntimeVO.setName(preCheckRunTime.getCheckModule().getName());
        preInspectionCheckRuntimeVO.setId(preCheckRunTime.getId());
        preInspectionCheckRuntimeVO.setDeadline(preCheckRunTime.getDeadline());
//        model.addAttribute("realmodel", preInspectionVO);
        model.addAttribute("checkruntime_model", preInspectionCheckRuntimeVO);
        model.addAttribute("precheck", preInspectionShowVO);
        model.addAttribute("precheckitems", preCheckItems);


        return PRE_DO_VIEW;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") final BigInteger id, Model model) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRESTART)) {
            return NO_AUTH_VIEW_NAME;
        }

        PreInspection po = preInspectionService.findByProjectFacadeId(id);
        PreInspectionVO preInspectionVO = new PreInspectionVO();
        preInspectionVO.setScore(po.getScore());
        preInspectionVO.setPreSDate(po.getPreSDate());
        preInspectionVO.setPreFDate(po.getPreFDate());
        preInspectionVO.setStatus(po.getStatus());
        preInspectionVO.setProjectName(po.getProjectFacade().getName());
        preInspectionVO.setCheckModuleID(po.getPreCheckModuleRoot().getId());
        preInspectionVO.setId(po.getId());
        model.addAttribute("preIns", preInspectionVO);

        return PRE_DETAIL_VIEW;

      /*  M treeModel = service.findOne(getRealID());

        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String s = binder.toJson(treeModel);
        return "[" + s + "]";*/

    }


    @ResponseBody
    @RequestMapping(value = "/docheck", method = RequestMethod.POST)
    public String doCheck(@Valid @ModelAttribute("checkruntime_model") final PreInspectionCheckRuntimeVO realmodel,
                          final BindingResult result, HttpSession httpSession) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRE_DO)) {
            return NO_AUTH_STRING;
        }

        if (result.hasErrors()) {
            return "error异常（非页面提交，你是机器人？）";
        }

        PreCheckRunTime preCheckRunTime = preCheckRunTimeRepos.findOne(realmodel.getId());

        if (!preCheckRunTime.getChecker().getUsername().equals(httpSession.getAttribute(StaticParams.SESSIONKEY.USERNAME))) {
            return NO_AUTH_STRING;
        }

        preCheckRunTime.setFinishDate(new Date());
        preCheckRunTime.setCheckRemark(realmodel.getCheckRemark());
        preCheckRunTime.setScore(realmodel.getScore());
        preCheckRunTime.setStatus(CHECKITEM_DONE);

        preCheckRunTimeRepos.save(preCheckRunTime);


        if (!Objects.equals(realmodel.getItemgrade(), "")) {
            String strtemp = realmodel.getItemgrade().substring(1);
            for (String str : strtemp.split(",")) {
                PreCheckItemRunTime preCheckItemRunTime = new PreCheckItemRunTime();
                preCheckItemRunTime.setFinishDate(preCheckRunTime.getFinishDate());
                preCheckItemRunTime.setPreInspection(preCheckRunTime.getPreInspection());
                preCheckItemRunTime.setCheckitem(preCheckItemService.findOne(
                        BigInteger.valueOf(Long.valueOf(str.split(":")[0]))));
                preCheckItemRunTime.setScore(Integer.valueOf(str.split(":")[1]));
                preCheckItemRunTimeRepos.save(preCheckItemRunTime);
            }
        }


        MyWork myWork = myWorkService.findByWorkUri(PJ_PRE_CHECKITEM_DO_WORKURI
                + realmodel.getId());
        if (myWork != null) {
            myWorkService.markDone(myWork);
        }

        PJPreCheckItemDoneEvent pjPreCheckItemDoneEvent = new PJPreCheckItemDoneEvent();
        pjPreCheckItemDoneEvent.setCheckitemruntimeid(preCheckRunTime.getId());
        publisher.publishEvent(pjPreCheckItemDoneEvent);

        System.out.println();
        return "ok";
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("realmodel") final PreInspectionVO realmodel) {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRESTART)) {
            return NO_AUTH_STRING;
        }
        System.out.println();


        PreInspection preInspection = new PreInspection();
        preInspection.setName("预稽查");

        User leader = userService.findOne(realmodel.getPreleader());
        Set<User> userList = new HashSet<>();

        for (String str : realmodel.getPrecheckers().split(",")) {
            User userTmp = userService.findOne(BigInteger.valueOf(Long.valueOf(str)));
            userList.add(userTmp);
        }

        preInspection.setLeader(leader);
        preInspection.setUser(userList);
        preInspection.setProjectFacade(facadeService.findOne(realmodel.getPjID()));
        preInspection.setStatus((short) 1);
        preInspection.setPreSDate(realmodel.getPreSDate());
        preInspection.setPreFDate(realmodel.getPreFDate());
        preInspection.setPreCheckModuleRoot(preCheckModuleService.findRootByTemName(realmodel.getCheckModuleName()));
        preInspectionService.save(preInspection);

        for (String str : realmodel.getCheckStr().split(",")) {
            User user = userService.findOne(BigInteger.valueOf(Long.valueOf(str.split(":")[1])));
            PreCheckRunTime preCheckRunTime = new PreCheckRunTime();
            try {
                preCheckRunTime.setDeadline(SDF.parse(str.split(":")[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            preCheckRunTime.setStatus((short) 1);
            preCheckRunTime.setCheckModule(preCheckModuleRepos.findOne(BigInteger.valueOf(Long.valueOf(str.split(":")[0]))));
            preCheckRunTime.setChecker(user);
            preCheckRunTime.setPreInspection(preInspection);
            preCheckRunTimeRepos.save(preCheckRunTime);

            PJPreConfEvent pjPreConfEvent = new PJPreConfEvent();
            pjPreConfEvent.setCheckerid(user.getId());
            pjPreConfEvent.setCheckitemruntimeid(preCheckRunTime.getId());
            pjPreConfEvent.setPjid(realmodel.getPjID());
//            pjPreConfEvent.setDeadline(p.getDeadline());
            pjPreConfEvent.setEventKey(StaticParams.TODOWORK.PJ_PRECONF_KEY);
            publisher.publishEvent(pjPreConfEvent);

        }

        ProjectFacade projectFacade = facadeService.findOne(realmodel.getPjID());
        projectFacade.setStatus((short) 2);
        facadeService.save(projectFacade);

        return "ok";
    }


    @RequestMapping("/chkmodule/result")
    @ResponseBody
    public String listTree(@RequestParam(value = "id", required = false) final BigInteger id) {

        PreCheckModule preCheckModuleRoot = preInspectionService.findOne(id).getPreCheckModuleRoot();

        PreCheckModuleVO preCheckModuleVO = new PreCheckModuleVO();

        preCheckModuleVO.setId(preCheckModuleRoot.getId());
        preCheckModuleVO.setName(preCheckModuleRoot.getName());
        preCheckModuleVO.setFolder(preCheckModuleRoot.getFolder());
        preCheckModuleVO.setResWeight(preCheckModuleRoot.getResWeight());

        if (preCheckModuleRoot.getChildren().size() != 0) {
            visitChildren(preCheckModuleRoot, preCheckModuleVO, id);
        }


//        return "";

        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String s = binder.toJson(preCheckModuleVO);
        return "[" + s + "]";

    }

    private void visitChildren(PreCheckModule preCheckModule, PreCheckModuleVO preCheckModuleVO, BigInteger preID) {

        for (PreCheckModule preCheckModuleTemp : preCheckModule.getChildren()) {

            PreCheckModuleVO preCheckModuleVOTemp = new PreCheckModuleVO();

            int itemp = 0;
            int score = 0;

            preCheckModuleVOTemp.setId(preCheckModuleTemp.getId());
            preCheckModuleVOTemp.setName(preCheckModuleTemp.getName());
            preCheckModuleVOTemp.setFolder(preCheckModuleTemp.getFolder());
            preCheckModuleVOTemp.setResWeight(preCheckModuleTemp.getResWeight());
            if (preCheckModuleTemp.getChildren().size() == 0) {
                PreCheckRunTime preCheckRunTime = preCheckRunTimeService.realfind(preID,
                        preCheckModuleTemp.getId());

                preCheckModuleVOTemp.setScore(preCheckRunTime.getScore());
                preCheckModuleVOTemp.setComment(preCheckRunTime.getCheckRemark());
                preCheckModuleVOTemp.setCheckerName(preCheckRunTime.getChecker().getNickname());
            } else {
                visitChildren(preCheckModuleTemp, preCheckModuleVOTemp, preID);
            }

            preCheckModuleVO.getChildren().add(preCheckModuleVOTemp);
        }

    }

}
