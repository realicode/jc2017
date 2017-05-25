package com.realaicy.prod.jc.modules.pj.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface PreInspectionService extends BaseServiceWithVO<PreInspection, BigInteger, PreInspectionVO> {

    PreInspection findByProjectFacadeId(BigInteger projectid);

}
