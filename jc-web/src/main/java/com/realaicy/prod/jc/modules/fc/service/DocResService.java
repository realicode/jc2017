package com.realaicy.prod.jc.modules.fc.service;




import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.fc.model.DocRes;
import com.realaicy.prod.jc.modules.fc.model.vo.DocResVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface DocResService extends BaseServiceWithVO<DocRes, BigInteger, DocResVO> {

    Short findTopWeightByPID(BigInteger parentID);

    DocRes findMyFile(short resBelongType, Boolean orgRootFlag, BigInteger belongID);
}
