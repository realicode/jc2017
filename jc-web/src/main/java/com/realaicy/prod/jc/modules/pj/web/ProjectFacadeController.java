package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.vo.ProjectFacadeVO;
import com.realaicy.prod.jc.modules.pj.service.ProjectFacadeService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/pj/facade")
public class ProjectFacadeController extends CRUDWithVOController<ProjectFacade, BigInteger, ProjectFacadeVO> {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final MyWorkService myWorkService;

    private static Specification<ProjectFacade> projectFacadeByUserName(String userName) {
        return (userRoot, query, cb) -> cb.equal(userRoot.get("applyUser").get("userName"), userName);
    }

    private ProjectFacadeService projectFacadeService;

    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "pj/facade/page";
    private static final String SHOW_ENTITY_URL = "pj/facade/detail";
    private static final String NEW_ENTITY_URL = "pj/facade/add";
    private static final String EDIT_ENTITY_URL = "pj/facade/add";
    private static final String LIST_ENTITY_URL = "pj/facade/page";
    private static final String SEARCH_ENTITY_URL = "pj/facade/search";

    private static final String PRE_CONF_VIEW = "pj/pre/wizard";

    private static final String AUTH_PREFIX = "PJ";
    private static final String AUTH_KEY_PRECONF = "pre-conf";
    private static final String AUTH_KEY_APPROVE = "approve";


    @Autowired
    public ProjectFacadeController(ProjectFacadeService projectFacadeService,
                                   UserService userService, ApplicationEventPublisher publisher, MyWorkService myWorkService) {
        super(projectFacadeService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                ProjectFacade.class, ProjectFacadeVO.class, BINDING_WHITE_LIST);
        this.projectFacadeService = projectFacadeService;
        this.userService = userService;
        this.publisher = publisher;
        this.myWorkService = myWorkService;
    }




    @Override
    protected void addAttrToModel(Model model) {

        if (SpringSecurityUtil.hasPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRECONF)) {
            model.addAttribute("auth_pj_pre_conf", "1");
        }

    }

    @Override
    protected List<ProjectFacadeVO> convertFromPOListToVOList(List<ProjectFacade> poList) {
        List<ProjectFacadeVO> projectFacadeVOList = new ArrayList<>();
        ProjectFacadeVO projectFacadeVOTemp;
        for (ProjectFacade po : poList) {
            projectFacadeVOTemp = new ProjectFacadeVO(po);
            projectFacadeVOList.add(projectFacadeVOTemp);
        }
        return projectFacadeVOList;
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
    protected void checkBeforeSaveNew(ProjectFacadeVO realmodel) throws SaveNewException {

    }


    @Override
    protected Specification<ProjectFacade> addSpec() {

        if (SpringSecurityUtil.hasPrivilege("superop")) {
            return null;
        }

        //noinspection ConstantConditions
        return projectFacadeByUserName(SpringSecurityUtil.getCurrentRealUserDetails().getUsername());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
