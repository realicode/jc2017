package com.realaicy.prod.jc.modules.pj.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface PreInspectionRepos extends BaseJPARepository<PreInspection, BigInteger> {

    PreInspection findByProjectFacadeId(BigInteger projectid);


}
