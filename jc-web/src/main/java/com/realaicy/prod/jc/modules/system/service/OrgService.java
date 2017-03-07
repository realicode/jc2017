package com.realaicy.prod.jc.modules.system.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface OrgService extends BaseServiceWithVO<Org, BigInteger, OrgVO> {

    List<Org> findByRegion(String region);
    List<Org> findByProvince(String province);
    boolean canBeDelete(Org entity);

}
