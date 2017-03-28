package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.event.PJPreConfEvent;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItemRunTime;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRunTimeRepos;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionCheckItemRuntimeVO;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionVO;
import com.realaicy.prod.jc.modules.pj.service.PreInspectionService;
import com.realaicy.prod.jc.modules.pj.service.ProjectFacadeService;
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

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;

@Controller
@RequestMapping("/pj/pre")
public class PreInspectionController {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    private static final String PRE_CONF_VIEW = "pj/pre/wizard";


    private static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    private static final String NO_AUTH_STRING = "NOPrivilege";

    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_PRECONF = "pre-conf";

    private final UserService userService;
    private final ProjectFacadeService facadeService;
    private final PreInspectionService preInspectionService;
    private final PreCheckItemRepos preCheckItemRepos;
    private final PreCheckItemRunTimeRepos preCheckItemRunTimeRepos;
    private final ApplicationEventPublisher publisher;



    @Autowired
    public PreInspectionController(UserService userService, ProjectFacadeService facadeService,
                                   PreInspectionService preInspectionService,
                                   PreCheckItemRepos preCheckItemRepos,
                                   PreCheckItemRunTimeRepos preCheckItemRunTimeRepos, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.facadeService = facadeService;
        this.preInspectionService = preInspectionService;
        this.preCheckItemRepos = preCheckItemRepos;
        this.preCheckItemRunTimeRepos = preCheckItemRunTimeRepos;
        this.publisher = publisher;
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
        PreInspectionVO preInspectionVO = new PreInspectionVO();
        preInspectionVO.setPjID(pjid);
        List<PreInspectionCheckItemRuntimeVO> preInspectionCheckItemRuntimeVOList = new ArrayList<>();
        preInspectionCheckItemRuntimeVOList.add(new PreInspectionCheckItemRuntimeVO(BigInteger.valueOf(1), "伦理相关", new Date()));
        preInspectionCheckItemRuntimeVOList.add(new PreInspectionCheckItemRuntimeVO(BigInteger.valueOf(2), "机构相关", new Date()));
        preInspectionCheckItemRuntimeVOList.add(new PreInspectionCheckItemRuntimeVO(BigInteger.valueOf(3), "药品相关", new Date()));
        preInspectionCheckItemRuntimeVOList.add(new PreInspectionCheckItemRuntimeVO(BigInteger.valueOf(4), "病例相关", new Date()));
        preInspectionCheckItemRuntimeVOList.add(new PreInspectionCheckItemRuntimeVO(BigInteger.valueOf(5), "其他相关", new Date()));

        preInspectionVO.setCheckitems(preInspectionCheckItemRuntimeVOList);
        model.addAttribute("preleaders", preInspectionLeaders);
        model.addAttribute("precheckers", preInspectionCheckers);
        model.addAttribute("realmodel", preInspectionVO);

        return PRE_CONF_VIEW;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("realmodel") final PreInspectionVO realmodel,
                       final BindingResult result) {


        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRECONF)) {
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

        preInspectionService.save(preInspection);

        for (PreInspectionCheckItemRuntimeVO p : realmodel.getCheckitems()) {
             User user = userService.findOne(p.getChecker());
            PreCheckItemRunTime preCheckItemRunTime = new PreCheckItemRunTime();
            preCheckItemRunTime.setDeadline(p.getDeadline());
            preCheckItemRunTime.setStatus((short) 1);
            preCheckItemRunTime.setCheckitem(preCheckItemRepos.findOne(p.getCheckitemID()));
            preCheckItemRunTime.setChecker(user);
            preCheckItemRunTime.setPreInspection(preInspection);
            preCheckItemRunTimeRepos.save(preCheckItemRunTime);

            PJPreConfEvent pjPreConfEvent = new PJPreConfEvent();
            pjPreConfEvent.setCheckerid(p.getChecker());
            pjPreConfEvent.setCheckitemruntimeid(p.getCheckitemID());
            pjPreConfEvent.setPjid(realmodel.getPjID());
            pjPreConfEvent.setDeadline(p.getDeadline());
            pjPreConfEvent.setEventKey(StaticParams.TODOWORK.PJ_PRECONF_KEY);
            publisher.publishEvent(pjPreConfEvent);

        }

        ProjectFacade  projectFacade = facadeService.findOne(realmodel.getPjID());
        projectFacade.setStatus((short) 2);
        facadeService.save(projectFacade);





        return "ok";
    }

}
