package com.realaicy.prod.jc.modules.medicinedb;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by realaicy on 16/6/2.
 * xx
 */
@Controller
@RequestMapping("/medicinedb/meddra/")
public class MedDraController extends CRUDWithVOController<MedDra, BigInteger, MedDraVO> {


    private static final  String[] NAME_DIC = {"name"};
    private static final  List<String> BINDING_WHITE_LIST = Collections.singletonList("name");
    private static final  String PAGE_URL = "medicinedb/meddra/page";
    private static final  String NEW_ENTITY_URL = "medicinedb/meddra/add";
    private static final  String EDIT_ENTITY_URL = "medicinedb/meddra/add";
    private static final  String LIST_ENTITY_URL = "medicinedb/meddra/page";
    private static final  String SEARCH_ENTITY_URL = "medicinedb/meddra/search";

    private Logger log = LoggerFactory.getLogger(getClass());
    private static JsonMapper binder = JsonMapper.nonDefaultMapper();


    @Autowired
    public MedDraController(MedDraService medDraService) {
        super(medDraService, "org", NAME_DIC, PAGE_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, MedDra.class, MedDraVO.class, BINDING_WHITE_LIST);
    }


    @RequestMapping("/list")
    @ResponseBody
    public String listTree(@RequestParam(value = "id", required = false) final BigInteger id,
                           @RequestParam(value = "pcode", required = false) final BigInteger pcode) {

        log.info("listTree: id:" + id);

        if (!checkAuth("c", MedDra.class.getSimpleName())) {
            return getNoAuthString();
        }

        List<MedDra> medDraList = new ArrayList<>();
        if (pcode != null) {
            medDraList = ((MedDraService) getService()).findByPCode(pcode);
        } else {
            medDraList = ((MedDraService) getService()).findByLevel("0");

        }

        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String s = binder.toJson(medDraList);

        log.info("medDraList: {} ", s);

        return s;
    }


    @Override
    protected void internalSaveNew(MedDraVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {

    }

    @Override
    protected MedDra internalSaveUpdate(MedDraVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        return null;
    }

    @Override
    protected void extendSave(MedDra po, BigInteger updateID, BigInteger pid) {

    }
}
