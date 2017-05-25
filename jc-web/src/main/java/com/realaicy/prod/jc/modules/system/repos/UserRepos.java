package com.realaicy.prod.jc.modules.system.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;
import com.realaicy.prod.jc.modules.system.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface UserRepos extends BaseJPARepository<User, BigInteger> {
    /**
     * 根据用户名查找实体
     * @param username 用户名
     * @return 返回用户实体
     */
    User findByUsername(String username);

    /**
     * 检查给定的用户名的用户的数量
     * @param username 用户名
     * @return 用户数量
     */
    @Query(value = "SELECT count(u.ID) FROM jc_sys_user u WHERE "
            + " u.USERNAME = :username AND u.F_DELETED = 0",
            nativeQuery = true)
    Integer checkUsername(@Param("username") String username);

    /**
     * 查找 1个 所属机构为"给定主键的机构正常状态（未被删除）"的用户的主键
     * @param orgID 机构主键
     * @return 用户主键
     */
    @Query(value = "SELECT u.ID FROM jc_sys_user u "
            + "WHERE u.ORG_ID = :orgID AND u.F_DELETED = 0 limit 1",
            nativeQuery = true)
    BigInteger findFirstNoDeletedUserIDByOrgIDNative(@Param("orgID") BigInteger orgID);

    @Query(value = "SELECT u.ID FROM jc_sys_user_role t, jc_sys_user u "
            + "WHERE t.USERID = u.ID AND t.ROLEID = :roleID AND u.F_DELETED = 0 limit 1",
            nativeQuery = true)
    BigInteger findFirstNoDeletedUserIDByRoleIDNative(@Param("roleID") BigInteger roleID);


    /**
     *
     * @param roleID xxx
     * @return xx
     */
    @Query(value = "SELECT u.ID, u.USERNAME FROM jc_sys_user_role t, jc_sys_user u "
            + "WHERE t.USERID = u.ID AND t.ROLEID = :roleID AND u.F_DELETED = 0",
            nativeQuery = true)
    List<User> findUsersByRoleIDNative(@Param("roleID") BigInteger roleID);


    User findByUserinfoWxuserid(String wxUserid);


    HashSet<PreInspectionUserVO> getRoleUsers(@Param("roleID") BigInteger roleID);



}
