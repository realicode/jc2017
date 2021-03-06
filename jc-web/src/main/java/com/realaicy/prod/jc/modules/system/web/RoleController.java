package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.vo.RoleVO;
import com.realaicy.prod.jc.modules.system.service.RoleService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
@RequestMapping("/system/role")
public class RoleController extends CRUDWithVOController<Role, BigInteger, RoleVO> {

    private RoleService roleService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "system/role/page";
    private static final String SHOW_ENTITY_URL = "system/role/detail";
    private static final String NEW_ENTITY_URL = "system/role/add";
    private static final String EDIT_ENTITY_URL = "system/role/add";
    private static final String LIST_ENTITY_URL = "system/role/page";
    private static final String SEARCH_ENTITY_URL = "system/role/search";


    @Autowired
    public RoleController(RoleService roleService) {
        super(roleService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, Role.class, RoleVO.class, BINDING_WHITE_LIST);
        this.roleService = roleService;
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return roleService.canBeDelete(id);
    }

    @Override
    protected void checkBeforeSaveNew(RoleVO realmodel) throws SaveNewException {
        if (roleService.checkOrgName(realmodel.getName())) {
            throw new SaveNewException("error角色名称已存在!");
        }
    }
}
