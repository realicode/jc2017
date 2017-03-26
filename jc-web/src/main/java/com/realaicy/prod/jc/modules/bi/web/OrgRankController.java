package com.realaicy.prod.jc.modules.bi.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.bi.model.OrgRank;
import com.realaicy.prod.jc.modules.bi.model.vo.OrgRankVO;
import com.realaicy.prod.jc.modules.bi.service.OrgRankService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/bi/rank/org")
public class OrgRankController extends CRUDWithVOController<OrgRank, BigInteger, OrgRankVO> {

    private OrgRankService orgRankService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "/bi/rank/org/page";
    private static final String NEW_ENTITY_URL = "null";
    private static final String SHOW_ENTITY_URL = "/bi/rank/org/detail";
    private static final String EDIT_ENTITY_URL = "null";
    private static final String LIST_ENTITY_URL = "/bi/rank/org/page";
    private static final String SEARCH_ENTITY_URL = "/bi/rank/org/search";


    @Autowired
    public OrgRankController(OrgRankService orgRankService) {
        super(orgRankService, NAME_DIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, OrgRank.class, OrgRankVO.class, BINDING_WHITE_LIST);
        this.orgRankService = orgRankService;
    }

    @RequestMapping("/detail")
    private String detail() {
        return SHOW_ENTITY_URL;
    }

    @Override
    protected boolean canBeDelete(BigInteger orgID) {
        return false;
    }

    @Override
    protected void checkBeforeSaveNew(OrgRankVO realmodel) throws SaveNewException {

    }
}
