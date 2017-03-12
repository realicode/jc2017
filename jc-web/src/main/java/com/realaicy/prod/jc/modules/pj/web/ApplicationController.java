package com.realaicy.prod.jc.modules.pj.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.modules.pj.model.Application;
import com.realaicy.prod.jc.modules.pj.model.ApplicationVO;
import com.realaicy.prod.jc.modules.pj.service.ApplicationService;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/pj/apply")
public class ApplicationController extends CRUDWithVOController<Application, BigInteger, ApplicationVO> {


    public static Specification<Application> applicaitonByUserName(String userName) {
        return new Specification<Application>() {
            @Override
            public Predicate toPredicate(final Root<Application> userRoot, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb) {

                return cb.equal(userRoot.get("user").get("username"), userName);

            }
        };
    }

    private ApplicationService applicationService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "pj/apply/page";
    private static final String NEW_ENTITY_URL = "pj/apply/add";
    private static final String EDIT_ENTITY_URL = "pj/apply/add";
    private static final String LIST_ENTITY_URL = "pj/apply/page";
    private static final String SEARCH_ENTITY_URL = "pj/apply/search";


    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        super(applicationService, "application", NAME_DIC, PAGE_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, Application.class, ApplicationVO.class, BINDING_WHITE_LIST);
        this.applicationService = applicationService;
    }


    @Override
    protected List<ApplicationVO> convertFromPOListToVOList(List<Application> poList) {
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        ApplicationVO applicationVOTemp;
        for(Application po:poList){
            applicationVOTemp = new ApplicationVO(po);
            applicationVOList.add(applicationVOTemp);
        }
        return applicationVOList;
    }

    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected boolean canBeDelete(Application entity) {
        return false;
    }

    @Override
    protected void internalSaveNew(ApplicationVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {

    }

    @Override
    protected Application internalSaveUpdate(ApplicationVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        return null;
    }

    @Override
    protected void extendSave(Application po, BigInteger updateID, BigInteger pid) {

    }

    @Override
    protected Specification<Application> addSpec() {
        return applicaitonByUserName(SpringSecurityUtil.getCurrentRealUserDetails().getUsername());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }
}
