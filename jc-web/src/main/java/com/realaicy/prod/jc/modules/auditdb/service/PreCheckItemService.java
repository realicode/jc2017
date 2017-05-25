package com.realaicy.prod.jc.modules.auditdb.service;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItem;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckItemVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface PreCheckItemService extends BaseServiceWithVO<PreCheckItem, BigInteger, PreCheckItemVO> {
    List<PreCheckItem> findByModuleID(BigInteger moduleid);
}
