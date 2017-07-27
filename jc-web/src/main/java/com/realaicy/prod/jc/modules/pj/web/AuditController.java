package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.event.PJDateExpertPubEvent;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRunTimeRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckModuleRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckRunTimeRepos;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckItemService;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckModuleService;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckRunTimeService;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import com.realaicy.prod.jc.modules.pj.model.vo.ExpertSupplyVO;
import com.realaicy.prod.jc.modules.pj.service.PreInspectionService;
import com.realaicy.prod.jc.modules.pj.service.ProjectFacadeService;
import com.realaicy.prod.jc.modules.pj.service.RecruitSupplyService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/audit")
public class AuditController {

    private static final String PRE_CONF_VIEW = "pj/pre/wizard";
    private static final String PRE_DO_VIEW = "pj/pre/docheck";
    private static final String AUDIT_DATEEXPERT_VIEW = "pj/audit/dateexpert";
    private static final String PRE_DETAIL_VIEW = "pj/pre/detail";
    private static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    private static final String NO_AUTH_STRING = "NOPrivilege";
    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_AUDIT_DATEEXPERT = "audit-dateexpert";
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
    public AuditController(UserService userService, ProjectFacadeService facadeService,
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


    @RequestMapping(value = "/dateexpert", method = RequestMethod.GET)
    public String renderRecruit(Model model,
                                @RequestParam(value = "pjid") BigInteger pjid) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_AUDIT_DATEEXPERT)) {
            return NO_AUTH_VIEW_NAME;
        }

        ExpertSupplyVO expertSupplyVO = new ExpertSupplyVO();
        expertSupplyVO.setWorkType(StaticParams.RECRUITTYPE.CHECK);
        expertSupplyVO.setProjectFacadeID(pjid);
        model.addAttribute("realmodel", expertSupplyVO);

        return AUDIT_DATEEXPERT_VIEW;
    }


    @ResponseBody
    @RequestMapping(value = "/dateexpert", method = RequestMethod.POST)
    public String pubRecruit(ExpertSupplyVO model, HttpSession httpSession) throws InstantiationException {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_AUDIT_DATEEXPERT)) {
            return NO_AUTH_VIEW_NAME;
        }

        System.out.println();

        RecruitSupply recruitSupply = new RecruitSupply();
        ProjectFacade projectFacade = facadeService.findOne(model.getProjectFacadeID());

        recruitSupply.setComment(model.getComment());
        recruitSupply.setEstStartDate(model.getRecruitSupplyVO1().getEstStartDate());
        recruitSupply.setEstEndDate(model.getRecruitSupplyVO1().getEstEndDate());
        recruitSupply.setWorkType(StaticParams.RECRUITTYPE.CHECK);
        recruitSupply.setProjectFacade(projectFacade);
        recruitSupply.setCreaterID((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
        recruitSupply.setCreateTime(new Date());
        recruitSupply.setUpdaterID(recruitSupply.getCreaterID());
        recruitSupply.setUpdateTime(recruitSupply.getCreateTime());
        recruitSupply.setDeleteFlag(false);
        recruitSupplyService.save(recruitSupply);

        RecruitSupply recruitSupply02 = new RecruitSupply();
        recruitSupply02.setComment(model.getComment());
        recruitSupply02.setEstStartDate(model.getRecruitSupplyVO2().getEstStartDate());
        recruitSupply02.setEstEndDate(model.getRecruitSupplyVO2().getEstEndDate());
        recruitSupply02.setWorkType(StaticParams.RECRUITTYPE.CHECK);
        recruitSupply02.setProjectFacade(projectFacade);
        recruitSupply02.setCreaterID((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
        recruitSupply02.setCreateTime(new Date());
        recruitSupply02.setUpdaterID(recruitSupply.getCreaterID());
        recruitSupply02.setUpdateTime(recruitSupply.getCreateTime());
        recruitSupply02.setDeleteFlag(false);
        recruitSupplyService.save(recruitSupply02);

        RecruitSupply recruitSupply03 = new RecruitSupply();
        recruitSupply03.setComment(model.getComment());
        recruitSupply03.setEstStartDate(model.getRecruitSupplyVO3().getEstStartDate());
        recruitSupply03.setEstEndDate(model.getRecruitSupplyVO3().getEstEndDate());
        recruitSupply03.setWorkType(StaticParams.RECRUITTYPE.CHECK);
        recruitSupply03.setProjectFacade(projectFacade);
        recruitSupply03.setCreaterID((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
        recruitSupply03.setCreateTime(new Date());
        recruitSupply03.setUpdaterID(recruitSupply.getCreaterID());
        recruitSupply03.setUpdateTime(recruitSupply.getCreateTime());
        recruitSupply03.setDeleteFlag(false);
        recruitSupplyService.save(recruitSupply03);

//        projectFacade.setPubPreStatus(PJPRE_RECRUIT_START);
//        facadeService.save(projectFacade);
//
        PJDateExpertPubEvent pjDateExpertPubEvent = new PJDateExpertPubEvent();
        pjDateExpertPubEvent.setEventKey(StaticParams.EVENTKEY.PJ_DATE_EXPERT_KEY);
        pjDateExpertPubEvent.setPjid(projectFacade.getId());
        pjDateExpertPubEvent.setCheckitemruntimeid(projectFacade.getId());
        publisher.publishEvent(pjDateExpertPubEvent);

        System.out.println();

        return StaticParams.RETURNMSG.SUCCESS;
    }

}
