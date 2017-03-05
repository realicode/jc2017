package com.realaicy.prod.jc.modules.system.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.Org;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface OrgRepos extends BaseJPARepository<Org, BigInteger> {

    List<Org> findByRegion(String region);
    List<Org> findByProvince(String province);

}
