package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashSet;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultUserService extends DefaultBaseServiceImpl<User, BigInteger>
        implements UserService {


    @Override
    public User findByUsername(String username) {
        return ((UserRepos) baseRepository).findByUsername(username);
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
    public User findByUserinfoWxuserid(String wxUserid) {
        return ((UserRepos) baseRepository).findByUserinfoWxuserid(wxUserid);
    }

    @Override
    public HashSet<PreInspectionUserVO> getUserByRole(BigInteger roleID) {
        return ((UserRepos) baseRepository).getRoleUsers(roleID);
    }


    @Override
    public void saveFromVO(User po, UserVO vo) {

    }
}
