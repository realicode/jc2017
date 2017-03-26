package com.realaicy.prod.jc.modules.system.web;

import com.google.common.base.Splitter;
import com.realaicy.prod.jc.common.exception.SaveNewException;
import com.realaicy.prod.jc.lib.core.mapper.JsonMapper;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.UserInfo;
import com.realaicy.prod.jc.modules.system.model.vo.User2RoleS2VO;
import com.realaicy.prod.jc.modules.system.model.vo.User2RoleVO;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.modules.system.service.OrgService;
import com.realaicy.prod.jc.modules.system.service.RoleService;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.realglobal.web.CRUDWithVOController;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.*;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.SESSIONKEY.USERID;
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
    private static final String USER_PROFILE_URL = "system/user/profile";

    private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    private final PasswordEncoder bcryptEncoder;
    private final UserRepos userRepos;
    private UserService userService;
    private UserInfoService userInfoService;

    private OrgService orgService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService,
                          PasswordEncoder bcryptEncoder, UserRepos userRepos, UserInfoService userInfoService, OrgService orgService) {
        super(userService, NAMEDIC, PAGE_URL, SHOW_ENTITY_URL, NEW_ENTITY_URL, EDIT_ENTITY_URL,
                LIST_ENTITY_URL, SEARCH_ENTITY_URL, User.class, UserVO.class, EDIT_BIND_WHITE_LIST);
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
        this.userRepos = userRepos;
        this.userInfoService = userInfoService;
        this.orgService = orgService;
        this.userService = userService;
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

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String userProfilePage(HttpSession httpSession, Model model) {

        UserInfo userInfo = userInfoService.findOne((BigInteger) httpSession.getAttribute(USERID));
        model.addAttribute("realmodel", userInfo);
        return USER_PROFILE_URL;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/sign")
    public String userSign(HttpSession httpSession) {

        UserInfo userInfo = userInfoService.findOne((BigInteger) httpSession.getAttribute(USERID));
        Date lastSignDay = userInfo.getLastSignDay();
        if (lastSignDay == null) {
            userInfo.setLastSignDay(new Date());
            userInfo.setContiSignDays(1);
        } else if (new Date().getTime() - lastSignDay.getTime() > 86400) {
            userInfo.setLastSignDay(new Date());
            if (new Date().getTime() - lastSignDay.getTime() < 172800) {
                userInfo.setContiSignDays(userInfo.getContiSignDays() + 1);
            } else {
                userInfo.setContiSignDays(1);
            }
        } else {
            return "error";
        }
        userInfoService.save(userInfo);
        return "ok";

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
        UserInfo userInfo = user.getUserinfo();
        String roleNames = "";
        user.getRoles().clear();

        if (user2role != null && !Objects.equals(user2role, "")) {
            for (String str : user2role.split(",")) {
                Role roleTemp = roleService.findOne(new BigInteger(str));
                user.getRoles().add(roleTemp);
                roleNames += roleTemp.getName();
                roleNames += ",";
            }
            userInfo.setRolenames(roleNames.substring(0, roleNames.length() - 1));
            userInfoService.save(userInfo);
            userRepos.save(user);
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
    protected boolean canBeDelete(BigInteger id) {
        return true;
    }


    @Override
    protected List<UserVO> convertFromPOListToVOList(List<User> poList) {

        List<UserVO> voList = new ArrayList<>();
        for (User userPO : poList) {
            UserVO userVO = new UserVO(userPO);
            String roleIDs = "";
            String roleNames = "";

            for (Role role : userPO.getRoles()) {
                roleIDs += role.getId();
                roleIDs += " || ";

                roleNames += role.getName();
                roleNames += " || ";
            }
            if (!Objects.equals(roleIDs, "")) {
                userVO.setRoleIDs(roleIDs.substring(0, roleIDs.length() - StaticParams.REALNUM.N3));

            }
            if (!Objects.equals(roleNames, "")) {
                userVO.setRoleNames(roleNames.substring(0, roleNames.length() - StaticParams.REALNUM.N3));
            }

            voList.add(userVO);
        }
        return voList;
    }

    @Override
    protected void extendShowEdit(User po, UserVO vo) {
        vo.setEmail(po.getUserinfo().getEmail());
        vo.setMobile(po.getUserinfo().getMobile());
        vo.setWxUserID(po.getUserinfo().getWxuserid());
        vo.setSex(po.getUserinfo().getSex());
        vo.setOrgRegion(po.getOrg().getRegion());
        vo.setOrgProvince(po.getOrg().getProvince());
        vo.setOrgName(po.getOrg().getName());
        vo.setOrgID(po.getOrg().getId().toString());
        vo.setPortraitUrl(po.getUserinfo().getPortraitUrl());

    }

    @Override
    protected void extendShowDetail(User po, UserVO vo) {

    }

    @Override
    protected void checkBeforeSaveNew(UserVO realmodel) throws SaveNewException {

        if (userService.checkUsername(realmodel.getUsername())) {
            throw new SaveNewException("error用户名称已存在!");
        }
    }

    @Override
    protected void extendSave(User po, UserVO realmodel) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(realmodel.getNickname());
        userInfo.setEmail(realmodel.getEmail());
        userInfo.setMobile(realmodel.getMobile());
        userInfo.setSex(realmodel.getSex());
        userInfo.setWxuserid(realmodel.getWxUserID());
        userInfo.setPortraitUrl(realmodel.getPortraitUrl());
        //noinspection ConstantConditions
        userInfo.setCreaterID(SpringSecurityUtil.getCurrentRealUserDetails().getId());
        userInfo.setUpdaterID(userInfo.getCreaterID());
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(userInfo.getCreateTime());

        po.setUserinfo(userInfo);

        po.setOrg(orgService.findOne(BigInteger.valueOf(Long.valueOf(realmodel.getOrgID()))));
        po.setPassword(bcryptEncoder.encode(realmodel.getPassword()));

        po.setAccountNonExpired(true);
        po.setCredentialsNonExpired(true);
        po.setAccountNonLocked(true);
        po.setEnabled(true);

        userInfoService.save(userInfo);
    }

    @Override
    protected String[] getNOEditPropertyNames() {
        return "password,accountNonExpired,accountNonLocked,credentialsNonExpired,enabled,roles".split(",");
    }

    @Override
    protected void extendSaveEdit(User po, UserVO realmodel) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(realmodel.getNickname());
        userInfo.setEmail(realmodel.getEmail());
        userInfo.setMobile(realmodel.getMobile());
        userInfo.setSex(realmodel.getSex());
        userInfo.setWxuserid(realmodel.getWxUserID());
        userInfo.setPortraitUrl(realmodel.getPortraitUrl());
        userInfo.setUpdaterID(userInfo.getCreaterID());
        userInfo.setUpdateTime(userInfo.getCreateTime());

        po.setUserinfo(userInfo);

        po.setOrg(orgService.findOne(BigInteger.valueOf(Long.valueOf(realmodel.getOrgID()))));

        userInfoService.save(userInfo);
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
