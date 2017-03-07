package com.realaicy.prod.jc.modules.system.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.vo.RoleVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface RoleService extends BaseServiceWithVO<Role, BigInteger, RoleVO> {


    Role findByRoleName(String roleName);

    List<Role> findByDeleteFlag(Boolean deleteFlag);

    boolean canBeDelete(Role entity);
}
