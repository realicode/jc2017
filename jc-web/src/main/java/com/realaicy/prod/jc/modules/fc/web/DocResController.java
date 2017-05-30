package com.realaicy.prod.jc.modules.fc.web;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.modules.fc.model.DocRes;
import com.realaicy.prod.jc.modules.fc.model.vo.DocResVO;
import com.realaicy.prod.jc.modules.fc.service.DocResService;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.web.TreeController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/fc/manager")
public class DocResController extends TreeController<DocRes, BigInteger, DocResVO> {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("name");
    private static final String PAGE_URL = "fc/manager/page";
    private static final String SHOW_ENTITY_URL = "fc/manager/add";
    private static final String NEW_ENTITY_URL = "fc/manager/add";
    private static final String EDIT_ENTITY_URL = "fc/manager/add";
    private static final String LIST_ENTITY_URL = "fc/manager/page";
    private static final String SEARCH_ENTITY_URL = "auditdb/pre/chkmodule/search";

    private static final String AUTH_PREFIX = "pre";
    private static final String AUTH_KEY_PRECONF = "chkmodule-addtotem";

    private final DocResService docResService;
    private final UserService userService;

    @Autowired
    public DocResController(DocResService docResService, UserService userService) {
        super(docResService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                DocRes.class, DocResVO.class, BINDING_WHITE_LIST);
        this.docResService = docResService;
        this.userService = userService;
    }


    @Override
    protected DocRes findOne(Map<String, Object> params) {
        return null;
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return false;
    }

    @Override
    protected void checkBeforeSaveNew(DocResVO realmodel) throws SaveNewException {

    }


    @Override
    protected String getTreeString(Map<String, Object> params) {

        DocRes docRes = docResService.findMyFile(Short.valueOf("1"), true, SpringSecurityUtil.getCurrentRealUserDetails().getId());
        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String returnStr = binder.toJson(docRes);

        logger.info("returnStr:{}",returnStr);

        for (Role role : userService.findOne(SpringSecurityUtil.getCurrentRealUserDetails().getId()).getRoles()) {
            DocRes docResTemp = docResService.findMyFile(Short.valueOf("2"), true, role.getId());
            if(docResTemp!=null){
                binder.getMapper().setFilterProvider(filters);
                String sTemp = binder.toJson(docResTemp);
                returnStr = returnStr + "," + sTemp;
            }
        }

        logger.info("returnStr2:{}",returnStr);

        return "[" + returnStr + "]";
    }

    @Override
    protected Short getResWeight(BigInteger pid) {
        Short result = docResService.findTopWeightByPID(pid);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
}
