package com.realaicy.prod.jc.modules.system.web;

import com.google.common.base.Splitter;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import com.realaicy.prod.jc.modules.system.model.vo.User2RoleS2VO;
import com.realaicy.prod.jc.modules.system.model.vo.User2RoleVO;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserSecRepos;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.modules.system.service.RoleService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.realaicy.prod.jc.uitl.RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
@RequestMapping("/system/user")
public class UserController extends CRUDWithVOController<User, BigInteger, UserVO> {

    private static final String[] NAMEDIC = {"username", "password", "nickname", "createTime"};
    @SuppressWarnings("unused")
    private static final List<String> EDIT_BIND_WHITE_LIST = Arrays.asList("username", "password");
    private static final String PAGE_URL = "system/user/page";
    private static final String SHOW_ENTITY_URL = "system/user/detail";
    private static final String NEW_ENTITY_URL = "system/user/add";
    private static final String EDIT_ENTITY_URL = "system/user/add";
    private static final String LIST_ENTITY_URL = "system/user/page";
    private static final String SEARCH_ENTITY_URL = "system/user/search";
    private static final String USER_TO_ROLE_URL = "system/user/user2role";
    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private final PasswordEncoder bcryptEncoder;
    private final UserSecRepos userSecRepos;
    private UserService userService;
    private OrgService orgService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService,
                          PasswordEncoder bcryptEncoder, UserSecRepos userSecRepos, OrgService orgService) {
        super(userService, "user", NAMEDIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, User.class, UserVO.class, EDIT_BIND_WHITE_LIST);
        this.userService = userService;
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
        this.userSecRepos = userSecRepos;
        this.orgService = orgService;
    }

    private static Specification<User> usersByOrgName(String orgName) {
        return (userRoot, query, cb) -> cb.like(userRoot.get("org").get("name"), orgName);
    }

    private static Specification<User> usersByRoleName(final String roleName) {
        return (userRoot, query, cb) -> {

            Subquery<Role> rolesubQuery = query.subquery(Role.class);
            Root<Role> roleRoot = rolesubQuery.from(Role.class);
            Expression<Collection<User>> roleUsers = roleRoot.get("users");
            rolesubQuery.select(roleRoot);
            rolesubQuery.where(cb.like(roleRoot.get("name"), roleName), cb.isMember(userRoot, roleUsers));
            return cb.exists(rolesubQuery);

        };
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checkusername", produces = "application/json")
    public String checkUsernameAvailable(@RequestParam(value = "username") String username, HttpServletRequest request) {
        String clientIP = NetUtil.getClientIpAddress(request);

        if (RealCacheUtil.IP_BLACK_LIST.contains(clientIP)) {
            return "ERROR:超过最大尝试次数";
        }

        if (IPADRESS_CANUSEFUNC_TABLE.get(clientIP, "checkusername") == null) {
            IPADRESS_CANUSEFUNC_TABLE.put(clientIP, "checkusername", 1);
        } else {
            IPADRESS_CANUSEFUNC_TABLE.put(clientIP, "checkusername", IPADRESS_CANUSEFUNC_TABLE.get(clientIP, "checkusername") + 1);
        }

        if (RealCacheUtil.IPADRESS_CANUSEFUNC_TABLE.get(clientIP, "checkusername") >= StaticParams.FUNCMAX.CHECKUSERNAME) {
            RealCacheUtil.IP_BLACK_LIST.add(clientIP);
        }
        return (Boolean.toString(!userService.checkUsername(username)));

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list4select", produces = "application/json")
    public Map<String, Object> findAllBySpecificationToSelect(
            @RequestParam(value = "q") String search) {

        Map<String, Object> info = new HashMap<>();

        List<User> users = userService.findByUsernameContaining(search);
        info.put("items", users);
        return info;
    }

    @RequestMapping(value = "/user2role/{userid}", method = RequestMethod.GET)
    public String userToRole(@PathVariable("userid") final BigInteger userid,
                             Model model) {

        User user = userService.findOne(userid);
        User2RoleVO user2RoleVO = productVONode(user.getRoles());
        model.addAttribute("user2role", binder.toJson(user2RoleVO));
        model.addAttribute("userid", userid);
        model.addAttribute("user2roles2", binder.toJson(productVOS2Node(user.getRoles())));
        return USER_TO_ROLE_URL;
    }


    @RequestMapping(value = "/u2rsave", method = RequestMethod.POST)
    @ResponseBody
    public String userToRoleSave(@RequestParam(value = "userid", required = false) BigInteger userid,
                                 @RequestParam(value = "user2role", required = false) String user2role) {

        User user = userService.findOne(userid);
        UserSec userSec = userSecRepos.findByUsername(user.getUsername());


        String roleNames = "";
        user.getRoles().clear();
        userSec.getRoles().clear();
        //user.setRolenames(roleNames);
        userService.save(user);
        userSecRepos.save(userSec);
        if (user2role != null && !Objects.equals(user2role, "")) {
            for (String str : user2role.split(",")) {
                Role roleTemp = roleService.findOne(new BigInteger(str));
                user.getRoles().add(roleService.findOne(new BigInteger(str)));
                userSec.getRoles().add(roleService.findOne(new BigInteger(str)));

                roleNames += roleTemp.getName();
                roleNames += ",";
            }
            user.setRolenames(roleNames.substring(0, roleNames.length() - 1));
            userService.save(user);
            userSecRepos.save(userSec);
        }

        return "ok";
    }

    private List<User2RoleS2VO> productVOS2Node(Set<Role> roles) {

        List<User2RoleS2VO> user2RoleS2VOList = new ArrayList<>();

        List<Role> rolesTemp = roleService.findByDeleteFlag(false);
        if (rolesTemp.size() > 1) {
            for (Role role : rolesTemp) {
                User2RoleS2VO user2RoleVOS2Temp = new User2RoleS2VO();
                user2RoleVOS2Temp.setId(role.getId());
                user2RoleVOS2Temp.setName(role.getName());
                if (roles.contains(role)) {
                    user2RoleVOS2Temp.setSelected(true);
                }

                user2RoleS2VOList.add(user2RoleVOS2Temp);
            }
        }
        return user2RoleS2VOList;
    }

    private User2RoleVO productVONode(Set<Role> roles) {
        User2RoleVO user2RoleVO = new User2RoleVO();
        user2RoleVO.setId(BigInteger.ONE);
        user2RoleVO.setName("请勾选角色");
        user2RoleVO.setFolder(true);
        user2RoleVO.setIfHideCheckbox(true);
        List<Role> rolesTemp = roleService.findByDeleteFlag(false);
        List<User2RoleVO> childrenTemp = new ArrayList<>();
        if (rolesTemp.size() > 1) {
            for (Role role : rolesTemp) {
                User2RoleVO user2RoleVOTemp = new User2RoleVO();
                user2RoleVOTemp.setId(role.getId());
                user2RoleVOTemp.setName(role.getName());
                if (roles.contains(role)) {
                    user2RoleVOTemp.setSelected(true);
                }

                childrenTemp.add(user2RoleVOTemp);
            }
        }
        user2RoleVO.setChildren(childrenTemp);
        return user2RoleVO;
    }

    @Override
    protected boolean needConvertForListDT() {
        return true;
    }

    @Override
    protected List<UserVO> convertFromPOListToVOList(List<User> poList) {
        return userService.convertFromPOListToVOList(poList);
    }

    @Override
    protected boolean canBeDelete(User entity) {
        return false;
    }


    @Override
    protected void extendShow(User po, UserVO realmodel) {
        realmodel.setOrgRegion(po.getOrg().getRegion());
        realmodel.setOrgProvince(po.getOrg().getProvince());
        realmodel.setOrgName(po.getOrg().getName());
        realmodel.setOrgID(po.getOrg().getId().toString());

    }

    @Override
    protected void internalSaveNew(UserVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {

        if (userService.findByName(realmodel.getUsername()) != null) {
            throw new SaveNewException("error用户名称已存在!");

        }

        realmodel.setPassword(bcryptEncoder.encode(realmodel.getPassword()));

        UserSec userSec = new UserSec();
        try {
            BeanUtils.copyProperties(userSec, realmodel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new SaveNewException(e.getMessage());

        }
        userSec.setAccountNonExpired(true);
        userSec.setCredentialsNonExpired(true);
        userSec.setAccountNonLocked(true);
        userSec.setEnabled(true);
        //userSecRepos.save(userSec);
        realmodel.setId(userSecRepos.save(userSec).getId());
//        realmodel.setOrgCascadeID(getOrgService().findOne(realmodel.getOrgID()).getCascadeID());
    }

    @Override
    protected void extendSave(User po, UserVO realmodel) {
        Org org = orgService.findOne(BigInteger.valueOf(Long.valueOf(realmodel.getOrgID())));
        po.setOrg(org);
    }

    @Override
    protected User internalSaveUpdate(UserVO realmodel, BigInteger updateID, BigInteger pid) throws SaveNewException {
        User user = userService.findOne(updateID);
        user.setNickname(realmodel.getNickname());
        user.setEmail(realmodel.getEmail());
        user.setSex(realmodel.getSex());
        return user;
    }

    @Override
    protected void extendSave(User po, BigInteger updateID, BigInteger pid) {

    }

    @Override
    protected Specification<User> getExtSpec(String str) {
        Map<String, String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        Specification<User> specification = null;
        if (map.containsKey("orgname")) {
            specification = usersByOrgName("%" + map.get("orgname") + "%");
        }
        if (map.containsKey("rolename")) {
            specification = Specifications.where(specification).and(usersByRoleName("%" + map.get("rolename") + "%"));
        }
        return specification;
    }
}
