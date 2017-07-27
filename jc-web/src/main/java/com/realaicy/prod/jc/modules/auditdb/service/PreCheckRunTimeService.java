package com.realaicy.prod.jc.modules.auditdb.service;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckRunTime;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckRunTImeVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface PreCheckRunTimeService extends BaseServiceWithVO<PreCheckRunTime, BigInteger, PreCheckRunTImeVO> {
    PreCheckRunTime realfind(BigInteger preID, BigInteger checkModuleID);
}
