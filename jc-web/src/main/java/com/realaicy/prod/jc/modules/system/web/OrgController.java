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
        super(orgService, "org", NAME_DIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, Org.class, OrgVO.class, BINDING_WHITE_LIST);
        this.orgService = orgService;
    }


    @Override
    protected boolean canBeDelete(Org entity) {
        return orgService.canBeDelete(entity);
    }

    @Override
    protected void internalSaveNew(OrgVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
//        if (roleService.findByNameWithInAOrg(realmodel.getName(), realmodel.getOrgID()) != null)
//            throw new SaveNewException("error角色名称已存在!");
    }

    @Override
    protected Org internalSaveUpdate(OrgVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        Org org = orgService.findOne(updateID);
        org.setName(realmodel.getName());
        org.setContactName(realmodel.getContactName());
        org.setRegion(realmodel.getRegion());
        org.setProvince(realmodel.getProvince());
        return org;
    }

    @Override
    protected void extendSave(Org po, BigInteger updateID, BigInteger pid) {

    }
}
