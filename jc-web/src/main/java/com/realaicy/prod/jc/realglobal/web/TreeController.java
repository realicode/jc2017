package com.realaicy.prod.jc.realglobal.web;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.CommonTreeableDeletableEntity;
import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.realaicy.prod.jc.lib.core.utils.RealBeanUtils.getNullPropertyNames;
import static com.realaicy.prod.jc.realglobal.config.StaticParams.REALWEIGHT.DEFAULT_ADD;

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

   /* @Override
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
    }*/

    public TreeController(BaseServiceWithVO<M, ID, V> service, String[] nameDic, String pageUrl,
                          String showEntityUrl, String newEntityUrl, String editEntityUrl, String listUrl,
                          String searchEntityUrl, Class<M> aClass, Class<V> voClass, List<String> editBindWhiteList) {
        super(service, nameDic, pageUrl, showEntityUrl, newEntityUrl, editEntityUrl,
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


    @RequestMapping(value = "/treenew", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "pid") final ID pid,
                           @RequestParam(value = "pname") final String pname) {

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
    public String listTree(@RequestParam(value = "id", required = false) final ID id,
                           @RequestParam(value = "temname", required = false) final String temName,
                           @RequestParam(value = "temid", required = false) final String temid) {

        log.info("listTree: id:" + id);
        log.info("listTree: temname:" + temName);

        if (!checkCRUDAuth("r", aClass.getSimpleName())) {
            return getNoAuthString();
        }

        Map<String, Object> params = new HashMap<>();
        if (temName != null) {
            params.put("temname", temName);
        }
        if (temid != null) {
            params.put("temid", temid);
        }

//        M treeModel = service.findOne(getRealID(params));
        M treeModel = this.findOne(params);

        FilterProvider filters = new SimpleFilterProvider().addFilter("realFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("updateTime"));
        binder.getMapper().setFilterProvider(filters);
        String s = binder.toJson(treeModel);
        return "[" + s + "]";
    }

    @RequestMapping(value = "/treesave", method = RequestMethod.POST)
    @ResponseBody
    public String saveModel(@Valid @ModelAttribute("realmodel") final V realmodel,
                            @RequestParam(value = "updateflag") String updateflag,
                            @RequestParam(value = "updateID") ID updateID,
                            @RequestParam(value = "pid") ID pid,
                            final BindingResult result, HttpSession httpSession) {
        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        if (updateflag.equals("new")) { //如果是新增保存
            if (result.hasErrors()) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }
            M po;
            try {
                po = aClass.newInstance();
                M parent = service.findOne(pid);

                String[] atemp = getNullPropertyNames(realmodel);
                BeanUtils.copyProperties(realmodel, po, atemp);

                final BaseSpecificationsBuilder<M> builder = new BaseSpecificationsBuilder<>();
                builder.with("cascadeID", "~", parent.getCascadeID(), "", "%");
                final Specification<M> spec = builder.build();
                Long childSize = service.count(spec);
                if (childSize == 1) {
                    po.setCascadeID(parent.getCascadeID() + ".001");
                } else {
                    po.setCascadeID(parent.getCascadeID() + "." + String.format("%03d", childSize));
                }
                po.setParent(parent);
                if (po.getResWeight() == null) {
                    po.setResWeight((short) (getResWeight(pid) + DEFAULT_ADD));
                }

                po.setDeleteFlag(false);
                po.setCreateTime(new Date());
                //noinspection unchecked,ConstantConditions
                po.setCreaterID((ID) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
                po.setUpdateTime(po.getCreateTime());
                po.setUpdaterID(po.getCreaterID());
                service.save(po);

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            M po = service.findOne(updateID);
            String[] atemp = getNullPropertyNames(realmodel);
            BeanUtils.copyProperties(realmodel, po, atemp);

            po.setUpdateTime(po.getCreateTime());
            //noinspection unchecked,ConstantConditions
            po.setUpdaterID((ID) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
            service.save(po);

        }
        return "ok";
    }

    protected M findOne(Map<String, Object> params) {
        return null;
    }

    protected Short getResWeight(ID pid) {
        return null;
    }

}
