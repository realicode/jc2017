package com.realaicy.prod.jc.modules.auditdb.web;


import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckModuleVO;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckModuleService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.TreeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.*;

import static com.realaicy.prod.jc.uitl.SpringSecurityUtil.hasAnyPrivilegeWithFuncByRealaicy;


/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/auditdb/pre/chkmodule/")
public class PreCheckModuleController extends TreeController<PreCheckModule, BigInteger, PreCheckModuleVO> {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("name");
    private static final String PAGE_URL = "auditdb/pre/chkmodule/page";
    private static final String SHOW_ENTITY_URL = "auditdb/pre/chkmodule/add";
    private static final String NEW_ENTITY_URL = "auditdb/pre/chkmodule/add";
    private static final String EDIT_ENTITY_URL = "auditdb/pre/chkmodule/add";
    private static final String LIST_ENTITY_URL = "auditdb/pre/chkmodule/page";
    private static final String SEARCH_ENTITY_URL = "auditdb/pre/chkmodule/search";

    private static final String TEM_URL = "auditdb/pre/chkmodule/tem";


    private static final String AUTH_PREFIX = "pre";
    private static final String AUTH_KEY_PRECONF = "chkmodule-addtotem";
    private static final String AUTH_KEY_TEM = "chkmodule-tem";


    private final PreCheckModuleService preCheckModuleService;

    @Autowired
    public PreCheckModuleController(PreCheckModuleService preCheckModuleService) {
        super(preCheckModuleService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                PreCheckModule.class, PreCheckModuleVO.class, BINDING_WHITE_LIST);
        this.preCheckModuleService = preCheckModuleService;
    }


    @RequestMapping(value = "/tem", method = RequestMethod.GET)
    public String listTemPage(Model model) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_TEM)) {
            return NO_AUTH_STRING;
        }

        List<String> allTems = preCheckModuleService.getTemNameList();
        model.addAttribute("allTems", allTems);

        return TEM_URL;
    }


    @RequestMapping("/addtotem/{nodestr}")
    @ResponseBody
    public String addToTem(@PathVariable("nodestr") String nodestr,
                           @RequestParam(value = "name") String name,
                           HttpSession httpSession) {

        if (!hasAnyPrivilegeWithFuncByRealaicy(AUTH_PREFIX, AUTH_KEY_PRECONF)) {
            return NO_AUTH_STRING;
        }

        logger.debug("nodestr:{} ; name:{}", nodestr, name);

        Map<BigInteger, PreCheckModule> checkModuleHashMap = new HashMap<>();
//        PreCheckModule nowParent = null;

        String[] souter = nodestr.split("_");

        if (preCheckModuleService.existTemName(name)) {
            return "error:模版名称已经存在";
        }

        PreCheckModule nowParent = preCheckModuleService.findOne(BigInteger.valueOf(2L));

        for (String outerTemp : souter) {
            String[] s = outerTemp.substring(1).split("\\$");
            for (String temp : s) {
                BigInteger tempID = BigInteger.valueOf(Long.valueOf(temp));
                if (checkModuleHashMap.containsKey(tempID)) {
                    nowParent = checkModuleHashMap.get(tempID);
                    continue;
                }
                PreCheckModule preCheckModule = preCheckModuleService.findOne(tempID);
                PreCheckModule preCheckModuleNew = new PreCheckModule();
                preCheckModuleNew.setName(preCheckModule.getName());
                preCheckModuleNew.setTemName(name);
                preCheckModuleNew.setRootlevel(preCheckModule.getRootlevel());
                preCheckModuleNew.setResWeight(preCheckModule.getResWeight());
                preCheckModuleNew.setShow(preCheckModule.getShow());
                preCheckModuleNew.setFolder(preCheckModule.getFolder());
                preCheckModuleNew.setAutoExpand(preCheckModule.getAutoExpand());
                preCheckModuleNew.setStatus(preCheckModule.getStatus());
                preCheckModuleNew.setCreateTime(new Date());
                preCheckModuleNew.setUpdateTime(preCheckModuleNew.getCreateTime());
                preCheckModuleNew.setCreaterID((BigInteger) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
                preCheckModuleNew.setUpdaterID(preCheckModuleNew.getCreaterID());
                preCheckModuleNew.setParent(nowParent);

                nowParent = preCheckModuleNew;

                preCheckModuleService.save(preCheckModuleNew);
                checkModuleHashMap.put(tempID, preCheckModuleNew);
            }
        }
        return "ok";
    }

    @Override
    protected void addAttrToModel(Model model) {
        List<String> allTems = preCheckModuleService.getTemNameList();
        model.addAttribute("allTems", allTems);
    }

    @Override
    protected PreCheckModule findOne(Map<String, Object> params) {
        if (params.containsKey("temid")) {
//           Long ltemp =  Long.valueOf(String.valueOf(params.get("temid")));
            BigInteger big1 = new BigInteger(params.get("temid").toString());
            return preCheckModuleService.findRootByTem(big1);
//            return preCheckModuleService.findOne(big1);

        } else if (params.containsKey("temname")) {
            return preCheckModuleService.findRootByTemName(params.get("temname").toString());
        }
        return preCheckModuleService.findOne(BigInteger.valueOf(3L));
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return preCheckModuleService.findOne(id).getChildren().size() <= 0;
    }

    @Override
    protected void checkBeforeSaveNew(PreCheckModuleVO realmodel) throws SaveNewException {

    }

    @Override
    protected Short getResWeight(BigInteger pid) {
        Short result = preCheckModuleService.findTopWeightByPID(pid);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
}
