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
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    protected static final String NO_AUTH_VIEW_NAME = "global/errorpage/NOPrivilege";
    protected static final String NO_AUTH_STRING = "NOPrivilege";

    private static String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
    private static Pattern p = Pattern.compile(regEx);
    private final BaseServiceWithVO<M, ID, V> service;
    private final String[] nameDic;
    private final String pageUrl;
    private final String showEntityUrl;
    private final String newEntityUrl;
    private final String editEntityUrl;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String listUrl;
    private final String searchEntityUrl;
    private final Class<M> aClass;
    private final Class<V> voClass;
    private final List<String> editBindWhiteList;

    /**
     * 构造函数
     *
     * @param service           服务基类
     * @param nameDic           啊
     * @param pageUrl           列表地址
     * @param showEntityUrl     查看地址
     * @param newEntityUrl      新增地址
     * @param editEntityUrl     编辑地址
     * @param listUrl           列表地址
     * @param searchEntityUrl   高级搜索地址
     * @param poClass           po
     * @param voClass           vo
     * @param editBindWhiteList 不校验的属性
     */
    public CRUDWithVOController(BaseServiceWithVO<M, ID, V> service, String[] nameDic, String pageUrl,
                                String showEntityUrl, String newEntityUrl, String editEntityUrl, String listUrl, String searchEntityUrl,
                                Class<M> poClass, Class<V> voClass, List<String> editBindWhiteList) {
        this.service = service;
        this.nameDic = nameDic;
        this.pageUrl = pageUrl;
        this.showEntityUrl = showEntityUrl;
        this.newEntityUrl = newEntityUrl;
        this.editEntityUrl = editEntityUrl;
        this.listUrl = listUrl;
        this.searchEntityUrl = searchEntityUrl;

        this.aClass = poClass;
        this.voClass = voClass;
        this.editBindWhiteList = editBindWhiteList;
    }

    /**
     * 展示列表页面
     *
     * @param model 模型数据
     * @return 返回新增页面地址
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listEntityPage(Model model) {
        if (checkCRUDAuth("r", aClass.getSimpleName())) {
            addAttrToModel(model);
            return this.pageUrl;
        } else {
            return NO_AUTH_VIEW_NAME;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        if (!checkCRUDAuth("s", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        return searchEntityUrl;
    }

    /**
     * 高级查找
     *
     * @param search 查找字符串
     * @return 返回PO列表
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/spec")
    public List<M> findAllBySpecification(@RequestParam(value = "search", required = false) final String search) {

        if (!checkCRUDAuth("s", aClass.getSimpleName())) {
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

    /**
     * page页面中的表格数据提供
     *
     * @param start      开始记录号
     * @param length     长度
     * @param orderIndex 排序
     * @param orderType  排序
     * @param search     条件
     * @param realsearch 扩展条件
     * @return 返回datatables需要的格式
     */
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

        if (!checkCRUDAuth("r", aClass.getSimpleName())) {
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

        if (needAddSpec()) {
            addSpec(builder);
        }
        Specification<M> spec = builder.build();

        if (needAddSpec()) {
            spec = Specifications.where(spec).and(addSpec());
        }

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

    /**
     * 查看实体详情
     *
     * @param id    实体主键
     * @param model 页面模型
     * @return 渲染后的实体详情页面
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showModel(@PathVariable("id") final ID id, Model model) {
        if (!checkCRUDAuth("r", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        try {
            V vo = voClass.newInstance();
            M po = service.findOne(id);
            BeanUtils.copyProperties(po, vo);
            extendShowDetail(po, vo);
            model.addAttribute("realmodel", vo);
            model.addAttribute("realUpdateID", id);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return showEntityUrl;
    }

    /**
     * 新增页面渲染
     *
     * @param model 模型
     * @return 渲染之后的新增页面
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newModel(Model model) {
        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        try {
            model.addAttribute("realmodel", voClass.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        model.addAttribute("realneworupdate", "new");
        addModelAttrToNew(model);
        return newEntityUrl;
    }

    /**
     * 新增页面渲染
     *
     * @param model 模型
     * @return 渲染之后的新增页面
     */
    @RequestMapping(value = "/new/{orgID}", method = RequestMethod.GET)
    public String newModel(@PathVariable("orgID") final BigInteger orgID, Model model) {
        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
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


    /**
     * 编辑页面渲染
     *
     * @param model 模型
     * @return 渲染之后的编辑页面
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showModel4Edit(@PathVariable("id") final ID id, Model model) {
        if (!checkCRUDAuth("u", aClass.getSimpleName())) {
            return NO_AUTH_VIEW_NAME;
        }
        try {
            V vo = voClass.newInstance();
            M po = service.findOne(id);
            BeanUtils.copyProperties(po, vo);
            extendShowEdit(po, vo);
            model.addAttribute("realmodel", vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        model.addAttribute("realUpdateID", id);
        return editEntityUrl;
    }


    /**
     * 保存实体
     *
     * @param realmodel   VO模型
     * @param result      绑定的结果
     * @param httpSession session
     * @param updateFlag  标志，用于判断是新增保存 还是编辑保存
     * @param updateID    如果是编辑的话，编辑实体的主键
     * @return 成功返回ok 失败返回err
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveModel(@Valid @ModelAttribute("realmodel") final V realmodel,
                            final BindingResult result, HttpSession httpSession,
                            @RequestParam(value = "updateflag", required = false) String updateFlag,
                            @RequestParam(value = "updateID", required = false) ID updateID) {

        if (!checkCRUDAuth("c", aClass.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        if (updateFlag.equals("new")) { //如果是新增保存
            if (result.hasErrors()) {
                return "error绑定异常（非页面提交，你是机器人？）";
            }
            try {
                //1.保存之前先进行额外的检查工作
                checkBeforeSaveNew(realmodel);
            } catch (SaveNewException e) {
                return e.getMessage();
            }

            try {
                //2.将基本的信息保存至PO对象
                M po = aClass.newInstance();
                String[] atemp = getNullPropertyNames(realmodel);
                BeanUtils.copyProperties(realmodel, po, atemp);

                po.setCreateTime(new Date());
                //noinspection unchecked,ConstantConditions
                po.setCreaterID((ID) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));
                po.setUpdateTime(po.getCreateTime());
                po.setUpdaterID(po.getCreaterID());

                //3.将额外的信息保存至PO对象
                extendSave(po, realmodel);

                ID newEntityID = service.save(po).getId();
                //4.保存成功之后的回掉函数
                saveNewEntityCallBack(newEntityID);

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }


            return "ok";

        } else if (updateFlag.equals("editedit")) { //如果是编辑保存
            for (FieldError fieldError : result.getFieldErrors()) {
                if (!editBindWhiteList.contains(fieldError.getField())) {
                    return "error绑定异常（非页面提交，你是机器人？）";
                }
            }

            M po = service.findOne(updateID);

            String[] atemp = getNullPropertyNames(realmodel);
            String[] btemp = getNOEditPropertyNames();

            BeanUtils.copyProperties(realmodel, po, ArrayUtils.addAll(atemp, btemp));

            po.setUpdateTime(new Date());
            //noinspection unchecked,ConstantConditions
            po.setUpdaterID((ID) httpSession.getAttribute(StaticParams.SESSIONKEY.USERID));

            extendSaveEdit(po, realmodel);

            service.save(po);

            return "ok";
        } // end of edit
        return null;
    }

    protected String[] getNOEditPropertyNames() {
        return null;
    }

    /**
     * 删除
     *
     * @param id 实体主键
     * @return 成功ok
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delModel(@PathVariable("id") final ID id) {

        if (!checkCRUDAuth("d", aClass.getSimpleName())) {
            return NO_AUTH_STRING;
        }

        M entity = service.findOne(id);
        if (canBeDelete(id)) {
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


    /**
     * page 页面添加额外的模型数据
     *
     * @param model 模型数据
     */
    protected void addAttrToModel(Model model) {
    }

    /**
     * page 页面添加额外的模型数据
     *
     * @param model 模型数据
     */
    protected void addModelAttrToNew(Model model) {
    }

    protected boolean needConvertForListDT() {
        return false;
    }

    protected List<V> convertFromPOListToVOList(List<M> poList) {
        return null;
    }

    protected void extendShowDetail(M po, V vo) {
    }

    protected void extendShowEdit(M po, V realmodel) {
    }

    protected void extendSave(M po, V realmodel) {
    }

    protected void extendSaveEdit(M po, V realmodel) {
    }

    protected abstract boolean canBeDelete(ID id);


    protected void saveNewEntityCallBack(ID id) {

    }


    protected abstract void checkBeforeSaveNew(V realmodel) throws SaveNewException;

    protected Specification<M> getExtSpec(String extSearchStr) {
        return null;
    }

    protected boolean needAddSpec() {
        return false;
    }

    private void addSpec(BaseSpecificationsBuilder<M> mBaseSpecificationsBuilder) {
    }

    protected Specification<M> addSpec() {
        return null;
    }


    protected boolean checkCRUDAuth(String reqAuthString, String objType) {
        return SpringSecurityUtil.hasPrivilege(objType + "-" + "a") || SpringSecurityUtil.hasPrivilege(objType + "-" + reqAuthString);
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

}
