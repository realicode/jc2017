package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.data.domain.PageRequest;
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
    public List<User> findByRoleID(BigInteger roleid) {

        return null;
    }


    @Override
    public void saveFromVO(User po, UserVO vo) {

    }
}
