package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.Role;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface RoleRepos extends BaseJPARepository<Role, BigInteger> {

    Role findByName(String roleName);

    List<Role> findByDeleteFlag(Boolean deleteFlag);

    Integer countByName(String roleName);

}
