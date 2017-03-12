package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultUserService extends DefaultBaseServiceImpl<User, BigInteger>
        implements UserService {


    @Override
    public User findByName(String username) {
        return ((UserRepos) baseRepository).findByUsername(username);
    }

    @Override
    public List<User> findAllUsersWithPage(PageRequest pageRequest) {

        return baseRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<User> findByUsernameContaining(String username) {
        return ((UserRepos) baseRepository).findTop10ByUsernameContaining(username);
    }

    @Override
    public Boolean ifHasNoDelUserByRole(Role role) {
        return ((UserRepos) baseRepository).findFirstNoDeletedUserIDByRoleIDNative(role.getId()) != null;
    }

    @Override
    public Boolean ifHasNoDelUserByOrg(Org org) {
        return ((UserRepos) baseRepository).findFirstNoDeletedUserIDByOrgIDNative(org.getId()) != null;

    }

    @Override
    public List<UserVO> convertFromPOListToVOList(List<User> poList) {

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
            if (!Objects.equals(roleIDs, "")){
                userVO.setRoleIDs(roleIDs.substring(0, roleIDs.length() - StaticParams.REALNUM.N3));

            }
            if(!Objects.equals(roleNames, "")){
                userVO.setRoleNames(roleNames.substring(0, roleNames.length() - StaticParams.REALNUM.N3));
            }

            voList.add(userVO);
        }
        return voList;

    }

    @Override
    public Boolean checkUsername(String username) {
        return ((UserRepos) baseRepository).checkUsername(username) >= 1;
    }


    @Override
    public void saveFromVO(User po, UserVO vo) {

    }
}
