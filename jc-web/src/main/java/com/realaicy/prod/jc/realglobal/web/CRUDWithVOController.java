package com.realaicy.prod.jc.realglobal.web;

import com.google.common.base.Joiner;
import com.realaicy.prod.jc.common.aop.annotations.Perfable;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.jpa.search.BaseSpecificationsBuilder;
import com.realaicy.prod.jc.lib.core.data.jpa.search.SearchOperation;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.data.plugin.LogicDeletable;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.realaicy.prod.jc.lib.core.utils.RealBeanUtils.getNullPropertyNames;

/**
 * Created by realaicy on 16/8/18.
 * xxx
 */
@Controller
public abstract class CRUDWithVOController<M extends BaseEntity<ID> & Commonable<ID>,
        ID extends Serializable, V extends BaseVO<ID>> {

    private static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    private static final String NO_AUTH_STRING = "NOPrivilege";
    private static String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
    private static Pattern p = Pattern.compile(regEx);
    private final BaseServiceWithVO<M, ID, V> service;
    private final String initFormParam;
    private final String[] nameDic;
    private final String pageUrl;
    private final String newEntityUrl;
    private final String editEntityUrl;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String listUrl;
    private final String searchEntityUrl;
    private final Class<M> aClass;
    private final Class<V> voClass;
    private final List<String> editBindWhiteList;

    public CRUDWithVOController(BaseServiceWithVO<M, ID, V> service, String initFormParam, String[] nameDic, String pageUrl,
                                String newEntityUrl, String editEntityUrl, String listUrl, String searchEntityUrl,
                                Class<M> poClass, Class<V> voClass, List<String> editBindWhiteList) {
        this.service = service;
        this.initFormParam = initFormParam;
        this.nameDic = nameDic;
        this.newEntityUrl = newEntityUrl;
        this.editEntityUrl = editEntityUrl;
        this.listUrl = listUrl;
        this.searchEntityUrl = searchEntityUrl;
        this.pageUrl = pageUrl;
        this.aClass = poClass;
        this.voClass = voClass;
        this.editBindWhiteList = editBindWhiteList;
    }

    protected static String getNoAuthString() {
        return NO_AUTH_STRING;
    }

    static String getNoAuthViewName() {
        return NO_AUTH_VIEW_NAME;
    }

    protected BaseServiceWithVO<M, ID, V> getService() {
        return service;
    }

//    protected Class<M> getaClass() {
//        return aClass;
//    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listEntityPage() {
        if (checkAuth("r", aClass.getSimpleName())) {
            System.out.println("pageUrl: " + pageUrl);
            return this.pageUrl;
        } else {
            return NO_AUTH_VIEW_NAME;
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newModel(Model model,
                           @RequestParam(value = "pid", required = false) ID pid,
                           @RequestParam(value = "pname", required = false) String pname) {
        if (!checkAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        try {
            model.addAttribute("realmodel", voClass.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        model.addAttribute("realneworupdate", "new");
        return newEntityUrl;
    }

    @RequestMapping(value = "/new/{orgID}", method = RequestMethod.GET)
    public String newModel(@PathVariable("orgID") final BigInteger orgID, Model model) {
        if (!checkAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }

        try {
            model.addAttribute("realmodel", voClass.newInstance());

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        model.addAttribute("realneworupdate", "new");
        model.addAttribute("orgID", orgID);
        return newEntityUrl;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showModel(@PathVariable("id") final ID id, Model model) {
        if (!checkAuth("r", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        try {
            V vo = voClass.newInstance();
            M po = service.findOne(id);
            BeanUtils.copyProperties(po, vo);
            extendShow(po, vo);
            model.addAttribute("realmodel", vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        model.addAttribute("realmodel", service.findOne(id));

        model.addAttribute("realUpdateID", id);
        return editEntityUrl;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        if (!checkAuth("s", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        return searchEntityUrl;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/spec")
    public List<M> findAllBySpecification(@RequestParam(value = "search", required = false) final String search) {

        if (!checkAuth("s", aClass.getSimpleName())) {
            return null;
        }

        final BaseSpecificationsBuilder<M> builder = new BaseSpecificationsBuilder<>();
        final String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        final Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)((\\p{L}|\\p{N})+?)(\\p{Punct}?),");

        final Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(StaticParams.REALNUM.N1), matcher.group(StaticParams.REALNUM.N2),
                    (p.matcher(matcher.group(StaticParams.REALNUM.N4)).replaceAll("").trim()),
                    matcher.group(StaticParams.REALNUM.N3),
                    "*");
        }
        final Specification<M> spec = builder.build();
        return service.findAll(spec);
    }

    @Perfable
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/list4dt")
    public Map<String, Object> findAllBySpecificationToDT(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "30") int length,
            @RequestParam(value = "order[0][column]", defaultValue = "1") int orderIndex,
            @RequestParam(value = "order[0][dir]", defaultValue = "asc") String orderType,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "realsearch", required = false) String realsearch
    ) {

        if (!checkAuth("r", aClass.getSimpleName())) {
            return null;
        }

        Map<String, Object> info = new HashMap<>();

        Sort sort;
        if (orderIndex > nameDic.length) {
            orderIndex = 1;
        }
        if (orderType.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, nameDic[orderIndex - 1]);
        } else {
            sort = new Sort(Sort.Direction.DESC, nameDic[orderIndex - 1]);
        }

        PageRequest pageRequest = new PageRequest(
                start / length, length, sort
        );
        //pageRequest.getSort().and(new Sort(Sort.Direction.ASC));

        final BaseSpecificationsBuilder<M> builder = new BaseSpecificationsBuilder<>();
        final String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        final Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)((\\p{L}|\\p{N})+?)(\\p{Punct}?),");
        final Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            System.out.println(matcher.group(StaticParams.REALNUM.N1));
            System.out.println(matcher.group(StaticParams.REALNUM.N2));
            System.out.println(matcher.group(StaticParams.REALNUM.N3));
            System.out.println(matcher.group(StaticParams.REALNUM.N4));
            builder.with(matcher.group(StaticParams.REALNUM.N1), matcher.group(StaticParams.REALNUM.N2),
                    (p.matcher(matcher.group(StaticParams.REALNUM.N4)).replaceAll("").trim()),
                    matcher.group(StaticParams.REALNUM.N3),
                    "*");
        }

        if (LogicDeletable.class.isAssignableFrom(aClass)) {
            builder.with("deleteFlag", ":", false, "", "");
        }

        Specification<M> spec = builder.build();

        if (realsearch != null && !realsearch.equals("")) {
            spec = Specifications.where(spec).and(getExtSpec(realsearch));
        }

        List<M> poList = service.findAll(spec, pageRequest);
        if (needConvertForListDT()) {
            info.put("data", convertFromPOListToVOList(poList));
        } else {
            info.put("data", poList);
        }

        info.put("recordsFiltered", service.count(spec));
        info.put("recordsTotal", service.count());
        return info;
    }

    protected boolean needConvertForListDT() {
        return false;
    }

    protected List<V> convertFromPOListToVOList(List<M> poList) {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String initForm(Model model) {
        System.out.println("M:" + model);
        //service.initializeForm(model);
        return initFormParam;   // Now initialized by the constructor
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CacheEvict(cacheResolver = "runtimeCacheResolver", allEntries = true)
    public String delModel(@PathVariable("id") final ID id) {

        if (!checkAuth("d", aClass.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        M entity = service.findOne(id);
        if (canBeDelete(entity)) {
            if (entity instanceof LogicDeletable) {
                ((LogicDeletable) entity).markDeleted();
                service.save(entity);
            } else {
                service.delete(entity);
            }
        } else {
            return "业务条件限制，无法删除";
        }

        return "ok";
    }

    protected abstract boolean canBeDelete(M entity);


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @CacheEvict(cacheResolver = "runtimeCacheResolver", allEntries = true)
    public String saveModel(@Valid @ModelAttribute("realmodel") final V realmodel,
                            final BindingResult result,
                            @RequestParam(value = "updateflag", required = false) String updateflag,
                            @RequestParam(value = "updateID", required = false) ID updateID,
                            @RequestParam(value = "pid", required = false) ID pid) {

        if (!checkAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        if (updateflag.equals("new")) {
            if (result.hasErrors()) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }
            try {
                internalSaveNew(realmodel, updateID, pid);
            } catch (SaveNewException e) {
                return e.getMessage();
            }

            try {
                M po = aClass.newInstance();
                String[] atemp = getNullPropertyNames(realmodel);
                BeanUtils.copyProperties(realmodel, po, atemp);

                po.setCreateTime(new Date());
                //noinspection unchecked,ConstantConditions
                po.setCreaterID((ID) SpringSecurityUtil.getCurrentPrincipal().getId());
                po.setUpdateTime(po.getCreateTime());
                po.setUpdaterID(po.getCreaterID());

                extendSave(po, updateID, pid);
                extendSave(po, realmodel);
                service.save(po);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return "ok";

        } else if (updateflag.equals("editedit")) { //edit
            for (FieldError fieldError : result.getFieldErrors()) {
                if (!editBindWhiteList.contains(fieldError.getField())) {
                    return "error绑定异常（非页面提交，你是机器人？）";
                }
            }

            try {
                M entity = internalSaveUpdate(realmodel, updateID, pid);

                entity.setUpdateTime(new Date());
                //noinspection unchecked,ConstantConditions
                entity.setUpdaterID((ID) SpringSecurityUtil.getCurrentPrincipal().getId());
                service.save(entity);

            } catch (SaveNewException e) {
                return e.getMessage();
            }
            return "ok";
        } // end of edit
        return null;
    }

    protected void extendSave(M po, V realmodel) {
    }

    protected void extendShow(M po, V realmodel) {
    }


    protected boolean checkAuth(String reqAuthString, String objType) {
        return SpringSecurityUtil.hasPrivilege(objType + "-" + "a") || SpringSecurityUtil.hasPrivilege(objType + "-" + reqAuthString);
    }

    protected abstract void internalSaveNew(V realmodel, ID updateID, ID pid) throws SaveNewException;

    protected abstract M internalSaveUpdate(V realmodel, ID updateID, ID pid) throws SaveNewException;

    protected abstract void extendSave(M po, ID updateID, ID pid);

    protected Specification<M> getExtSpec(String extSearchStr) {
        return null;
    }


}
