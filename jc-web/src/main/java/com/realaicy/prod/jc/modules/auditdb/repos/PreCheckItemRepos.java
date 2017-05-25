package com.realaicy.prod.jc.modules.auditdb.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItem;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface PreCheckItemRepos extends BaseJPARepository<PreCheckItem, BigInteger> {

    List<PreCheckItem> findByModuleID(BigInteger moduleid);
}
