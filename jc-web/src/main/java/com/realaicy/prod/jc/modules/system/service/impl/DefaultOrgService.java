package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;
import com.realaicy.prod.jc.modules.system.repos.OrgRepos;
import com.realaicy.prod.jc.modules.system.service.OrgService;
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
public class DefaultOrgService extends DefaultBaseServiceImpl<Org, BigInteger>
        implements OrgService {

    private final UserService userService;

    @Autowired
    public DefaultOrgService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean checkOrgName(String orgName) {
        return ((OrgRepos) baseRepository).countByName(orgName) >= 1;
    }

    @Override
    public boolean canBeDelete(BigInteger orgID) {
        return !userService.ifHasNoDelUserByOrgID(orgID);
    }

    @Override
    public void saveFromVO(Org po, OrgVO vo) {

    }
}
