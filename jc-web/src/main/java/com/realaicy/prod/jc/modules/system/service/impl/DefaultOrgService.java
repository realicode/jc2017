package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;
import com.realaicy.prod.jc.modules.system.repos.OrgRepos;
import com.realaicy.prod.jc.modules.system.service.OrgService;
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
public class DefaultOrgService extends DefaultBaseServiceImpl<Org, BigInteger>
        implements OrgService {


    @Override
    public List<Org> findByRegion(String region) {
        return ((OrgRepos) baseRepository).findByRegion(region);
    }

    @Override
    public List<Org> findByProvince(String province) {
        return ((OrgRepos) baseRepository).findByProvince(province);
    }

    @Override
    public void saveFromVO(Org po, OrgVO vo) {

    }
}
