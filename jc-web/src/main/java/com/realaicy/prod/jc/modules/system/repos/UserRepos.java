package com.realaicy.prod.jc.modules.system.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface UserRepos extends BaseJPARepository<User, BigInteger> {

    User findByUsername(String username);

    @Query(value = "SELECT u.ID FROM jc_sys_user_role t, jc_sys_user u "
            + "WHERE t.USERID = u.ID AND t.ROLEID = :roleID AND u.F_DELETED = 0 limit 1",
            nativeQuery = true)
    BigInteger findFirstNoDeletedUserIDByRoleIDNative(@Param("roleID") BigInteger roleID);

    @Query(value = "SELECT u.ID FROM jc_sys_user u "
            + "WHERE u.ORG_ID = :orgID AND u.F_DELETED = 0 limit 1",
            nativeQuery = true)
    BigInteger findFirstNoDeletedUserIDByOrgIDNative(@Param("orgID") BigInteger orgID);

    @Query(value = "SELECT count(u.ID) FROM jc_sys_user u "
            + "WHERE u.USERNAME = :username AND u.F_DELETED = 0",
            nativeQuery = true)
    Integer checkUsername(@Param("username") String username);

    List<User> findTop10ByUsernameContaining(String username);

}
