package com.realaicy.prod.jc.modules.me.web;

import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.model.vo.MyWorkVO;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/me/work")
public class MyWorkController extends CRUDWithVOController<MyWork, BigInteger, MyWorkVO> {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;


    private MyWorkService myWorkService;
    private static final String[] NAME_DIC = {"name"};
    private static final List<String> BINDING_WHITE_LIST = Collections.singletonList("");
    private static final String PAGE_URL = "me/work/page";
    private static final String SHOW_ENTITY_URL = "me/work/detail";
    private static final String NEW_ENTITY_URL = "me/work/add";
    private static final String EDIT_ENTITY_URL = "me/work/add";
    private static final String LIST_ENTITY_URL = "me/work/page";
    private static final String SEARCH_ENTITY_URL = "me/work/search";
    private static final String APPLY_CONFIRM_URL = "me/work/confirm";


    @Autowired
    public MyWorkController(MyWorkService myWorkService, UserService userService,
                            ApplicationEventPublisher publisher) {
        super(myWorkService, "myWork", NAME_DIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL,
                EDIT_ENTITY_URL, LIST_ENTITY_URL, SEARCH_ENTITY_URL, MyWork.class, MyWorkVO.class, BINDING_WHITE_LIST);
        this.myWorkService = myWorkService;
        this.userService = userService;
        this.publisher = publisher;
    }

    private static Specification<MyWork> worksByUsername(String username) {
        return (workRoot, query, cb) -> cb.like(workRoot.get("user").get("username"), username);
    }


    @Override
    protected List<MyWorkVO> convertFromPOListToVOList(List<MyWork> poList) {
        List<MyWorkVO> workVOList = new ArrayList<>();
        MyWorkVO myWorkVOTemp;
        for (MyWork po : poList) {
            myWorkVOTemp = new MyWorkVO(po);
            workVOList.add(myWorkVOTemp);
        }
        return workVOList;
    }

    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected boolean canBeDelete(MyWork entity) {
        return false;
    }

    @Override
    protected void internalSaveNew(MyWorkVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {

    }

    @Override
    protected MyWork internalSaveUpdate(MyWorkVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        return null;
    }

    @Override
    protected void extendSave(MyWork po, BigInteger updateID, BigInteger pid) {

    }

    @Override
    protected void addAttrToModel(Model model) {
        super.addAttrToModel(model);
    }

    @Override
    protected Specification<MyWork> addSpec() {
        return worksByUsername(SpringSecurityUtil.getNameOfCurrentPrincipal());
    }

    @Override
    protected boolean needAddSpec() {
        return true;
    }


}
