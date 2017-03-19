package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.modules.system.repos.UserSecRepos;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultUserService extends DefaultBaseServiceImpl<User, BigInteger>
        implements UserService {

    private final UserSecRepos userSecRepos;

    @Autowired
    public DefaultUserService(UserSecRepos userSecRepos) {
        this.userSecRepos = userSecRepos;
    }

    @Override
    public User findByUsername(String username) {
        return ((UserRepos) baseRepository).findByUsername(username);
    }

    @Override
    public UserSec getUserSecFromUsername(String username) {
        return userSecRepos.findByUsername(username);
    }

    @Override
    public Boolean checkUsername(String username) {
        return ((UserRepos) baseRepository).checkUsername(username) >= 1;
    }

    @Override
    public Boolean ifHasNoDelUserByRole(BigInteger roleID) {
        return ((UserRepos) baseRepository).findFirstNoDeletedUserIDByRoleIDNative(roleID) != null;
    }

    @Override
    public Boolean ifHasNoDelUserByOrgID(BigInteger orgID) {
        return ((UserRepos) baseRepository).findFirstNoDeletedUserIDByOrgIDNative(orgID) != null;
    }

    @Override
    public void saveFromVO(User po, UserVO vo) {

    }
}
