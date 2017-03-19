package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/system/org")
public class OrgController extends CRUDWithVOController<Org, BigInteger, OrgVO> {

    private OrgService orgService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "system/org/page";
    private static final String NEW_ENTITY_URL = "system/org/add";
    private static final String SHOW_ENTITY_URL = "system/org/detail";
    private static final String EDIT_ENTITY_URL = "system/org/add";
    private static final String LIST_ENTITY_URL = "system/org/page";
    private static final String SEARCH_ENTITY_URL = "system/org/search";


    @Autowired
    public OrgController(OrgService orgService) {
        super(orgService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, Org.class, OrgVO.class, BINDING_WHITE_LIST);
        this.orgService = orgService;
    }


    @Override
    protected boolean canBeDelete(BigInteger orgID) {
        return orgService.canBeDelete(orgID);
    }

    @Override
    protected void checkBeforeSaveNew(OrgVO realmodel) throws SaveNewException {
        if (orgService.checkOrgName(realmodel.getName())) {
            throw new SaveNewException("error机构名称已存在!");
        }
    }
}
