package com.realaicy.prod.jc.modules.auditdb.repos;



import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.auditdb.model.AuditCheckStep;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface AuditCheckStepRepos extends BaseJPARepository<AuditCheckStep, BigInteger> {

    AuditCheckStep findTopByCheckitemIDAndDeleteFlagOrderByStepNoDesc(BigInteger checkitemID, Boolean deleteFlag);
}
