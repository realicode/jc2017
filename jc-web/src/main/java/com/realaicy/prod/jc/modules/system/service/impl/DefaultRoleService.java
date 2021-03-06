package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.vo.RoleVO;
import com.realaicy.prod.jc.modules.system.repos.RoleRepos;
import com.realaicy.prod.jc.modules.system.service.RoleService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultRoleService extends DefaultBaseServiceImpl<Role, BigInteger>
        implements RoleService {

    private final UserService userService;

    @Autowired
    public DefaultRoleService(UserService service) {
        this.userService = service;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return ((RoleRepos) baseRepository).findByName(roleName);
    }

    @Override
    public Boolean checkOrgName(String orgName) {
        return ((RoleRepos) baseRepository).countByName(orgName) >= 1;
    }

    @Override
    public List<Role> findByDeleteFlag(Boolean deleteFlag) {
        return ((RoleRepos) baseRepository).findByDeleteFlag(deleteFlag);
    }

    @Override
    public boolean canBeDelete(BigInteger roleID) {
        return !userService.ifHasNoDelUserByRole(roleID);
    }

    @Override
    public void saveFromVO(Role po, RoleVO vo) {

    }
}
