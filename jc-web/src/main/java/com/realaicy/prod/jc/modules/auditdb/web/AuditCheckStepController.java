package com.realaicy.prod.jc.modules.auditdb.web;


import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.auditdb.model.AuditCheckStep;
import com.realaicy.prod.jc.modules.auditdb.model.vo.AuditCheckStepVO;
import com.realaicy.prod.jc.modules.auditdb.service.AuditCheckStepService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;


/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/auditdb/chkstep/")
public class AuditCheckStepController extends CRUDWithVOController<AuditCheckStep, BigInteger, AuditCheckStepVO> {

    private AuditCheckStepService auditCheckStepService;
    private static final  String[] NAME_DIC = {"stepNo"};
    private static final  List<String> BINDING_WHITE_LIST = Collections.singletonList("name");
    private static final  String PAGE_URL = "auditdb/chkstep/page";
    private static final String SHOW_ENTITY_URL = "auditdb/chkstep/detail";
    private static final  String NEW_ENTITY_URL = "auditdb/chkstep/add";
    private static final  String EDIT_ENTITY_URL = "auditdb/chkstep/add";
    private static final  String LIST_ENTITY_URL = "auditdb/chkstep/page";
    private static final  String SEARCH_ENTITY_URL = "auditdb/chkstep/search";

    @Autowired
    public AuditCheckStepController(AuditCheckStepService auditCheckStepService) {
        super(auditCheckStepService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                AuditCheckStep.class, AuditCheckStepVO.class, BINDING_WHITE_LIST);
        this.auditCheckStepService = auditCheckStepService;
    }

    @Override
    protected boolean canBeDelete(BigInteger id) {
        return false;
    }

    @Override
    protected void checkBeforeSaveNew(AuditCheckStepVO realmodel) throws SaveNewException {

//        realmodel.setCheckitemID(pid);
//        realmodel.setStepNo(auditCheckStepService.findTopByCheckitemIDOrderByStepNoDesc(pid) + 1);
    }

   /* @Override
    protected AuditCheckStep extendUpdate(AuditCheckStepVO realmodel,
                                          BigInteger updateID, BigInteger pid) throws SaveNewException {
        AuditCheckStep auditCheckStep = getService().findOne(updateID);
        BeanUtils.copyProperties(realmodel, auditCheckStep, getNullPropertyNames(realmodel));

        return auditCheckStep;
    }*/
}
