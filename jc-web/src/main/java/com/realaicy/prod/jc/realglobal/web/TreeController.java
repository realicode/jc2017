package com.realaicy.prod.jc.realglobal.web;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonTreeableDeletableEntity;
import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by realaicy on 16/8/18.
 * xxx
 */
@Controller
public abstract class TreeController<M extends CommonTreeableDeletableEntity<ID, M>,
        ID extends Serializable, V extends BaseVO<ID>> extends CRUDWithVOController<M, ID, V> {

    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private final BaseServiceWithVO<M, ID, V> service;
    private final Class<M> aClass;
    private final Class<V> voClass;
    private final String newEntityUrl;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void extendSave(M po, ID updateID, ID pid) {

        M parent = service.findOne(pid);
        po.setParent(parent);
        po.setDeleteFlag(false);

        final BaseSpecificationsBuilder<M> builder = new BaseSpecificationsBuilder<>();
        builder.with("cascadeID", ":", parent.getCascadeID(), "", "*");
        final Specification<M> spec = builder.build();
        Long childSize = service.count(spec);
        if (childSize == 1) {
            po.setCascadeID(parent.getCascadeID() + ".001");
        } else {
            po.setCascadeID(parent.getCascadeID() + "." + String.format("%03d", childSize));
        }
    }

    public TreeController(BaseServiceWithVO<M, ID, V> service, String initFormParam, String[] nameDic, String pageUrl,
                          String showEntityUrl, String newEntityUrl, String editEntityUrl, String listUrl,
                          String searchEntityUrl, Class<M> aClass, Class<V> voClass, List<String> editBindWhiteList) {
        super(service, initFormParam, nameDic, pageUrl, showEntityUrl, newEntityUrl, editEntityUrl,
                listUrl, searchEntityUrl, aClass, voClass, editBindWhiteList);
        this.service = service;
        this.newEntityUrl = newEntityUrl;
        this.aClass = aClass;
        this.voClass = voClass;
    }

    @SuppressWarnings("unused")
    public Class<M> getaClass() {
        return aClass;
    }


    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "pid", required = false) final ID pid,
                           @RequestParam(value = "pname", required = false) final String pname) {

        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
            return getNoAuthViewName();
        }

        try {
            model.addAttribute("realmodel", voClass.newInstance());
            model.addAttribute("pname", pname);
            model.addAttribute("pid", pid);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        model.addAttribute("realneworupdate", "new");
        return newEntityUrl;
    }

    @RequestMapping("/list")
    //@PreAuthorize("@realSecurityService.hasPermission(#root.target.getaClass().getSimpleName() + \"-\" + \"r\")")
    //@PreAuthorize("hasPermission('r',)")
    @ResponseBody
    public String listTree(@RequestParam(value = "id", required = false) final ID id) {

        log.info("listTree: id:" + id);

        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
            return getNoAuthString();
        }


        M treeModel;

        treeModel = service.findOne(getRealID());

        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String s = binder.toJson(treeModel);
        return "[" + s + "]";
    }


    protected abstract ID getRealID();


}
