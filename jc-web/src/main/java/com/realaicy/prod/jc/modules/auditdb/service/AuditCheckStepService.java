package com.realaicy.prod.jc.modules.auditdb.service;



import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.auditdb.model.AuditCheckStep;
import com.realaicy.prod.jc.modules.auditdb.model.vo.AuditCheckStepVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface AuditCheckStepService extends BaseServiceWithVO<AuditCheckStep, BigInteger, AuditCheckStepVO> {

    Integer findTopByCheckitemIDOrderByStepNoDesc(BigInteger checkitemID);
}
