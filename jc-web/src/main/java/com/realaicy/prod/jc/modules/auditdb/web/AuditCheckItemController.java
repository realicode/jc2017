package com.realaicy.prod.jc.modules.auditdb.web;


import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.auditdb.model.AuditCheckItem;
import com.realaicy.prod.jc.modules.auditdb.model.vo.AuditCheckItemVO;
import com.realaicy.prod.jc.modules.auditdb.service.AuditCheckItemService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.TreeController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static com.realaicy.prod.jc.lib.core.utils.RealBeanUtils.getNullPropertyNames;


/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/auditdb/chkitem/")
public class AuditCheckItemController extends TreeController<AuditCheckItem, BigInteger, AuditCheckItemVO> {

    private static final  String[] NAME_DIC = {"name"};
    private static final  List<String> BINDING_WHITE_LIST = Collections.singletonList("name");
    private static final  String PAGE_URL = "auditdb/chkitem/page";
    private static final  String SHOW_ENTITY_URL = "auditdb/chkitem/detail";
    private static final  String NEW_ENTITY_URL = "auditdb/chkitem/add";
    private static final  String EDIT_ENTITY_URL = "auditdb/chkitem/add";
    private static final  String LIST_ENTITY_URL = "auditdb/chkitem/page";
    private static final  String SEARCH_ENTITY_URL = "auditdb/chkitem/search";

    @Autowired
    public AuditCheckItemController(AuditCheckItemService auditCheckItemService) {
        super(auditCheckItemService, "org", NAME_DIC, PAGE_URL, SHOW_ENTITY_URL,
                NEW_ENTITY_URL, EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL,
                AuditCheckItem.class, AuditCheckItemVO.class, BINDING_WHITE_LIST);
    }

    @Override
    public BigInteger getRealID() {
        return BigInteger.valueOf(StaticParams.REALNUM.N3);
    }

    @Override
    protected boolean canBeDelete(AuditCheckItem entity) {
        return false;
    }

    @Override
    protected void internalSaveNew(AuditCheckItemVO realmodel, BigInteger updateID, BigInteger pid)
            throws SaveNewException {
    }

    @Override
    protected AuditCheckItem internalSaveUpdate(AuditCheckItemVO realmodel, BigInteger updateID, BigInteger pid)
            throws SaveNewException {
        AuditCheckItem auditCheckItem = getService().findOne(updateID);
        BeanUtils.copyProperties(realmodel, auditCheckItem, getNullPropertyNames(realmodel));

        return auditCheckItem;
    }
}
