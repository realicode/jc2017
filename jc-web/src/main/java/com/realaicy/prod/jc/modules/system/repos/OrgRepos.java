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

    Org findByName(String orgName);

    /**
     * 统计给定名字的 机构  的数量
     * @param orgName 机构名称
     * @return 返回数量
     */
    Integer countByName(String orgName);

}
