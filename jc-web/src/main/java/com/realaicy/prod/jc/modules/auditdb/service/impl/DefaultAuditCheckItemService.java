package com.realaicy.prod.jc.modules.auditdb.service.impl;


import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.auditdb.model.AuditCheckItem;
import com.realaicy.prod.jc.modules.auditdb.model.vo.AuditCheckItemVO;
import com.realaicy.prod.jc.modules.auditdb.service.AuditCheckItemService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
public class DefaultAuditCheckItemService extends DefaultBaseServiceImpl<AuditCheckItem, BigInteger>
        implements AuditCheckItemService {

    @Override
    public void saveFromVO(AuditCheckItem po, AuditCheckItemVO vo) {

    }
}
