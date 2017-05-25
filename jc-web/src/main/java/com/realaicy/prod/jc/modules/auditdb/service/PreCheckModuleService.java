package com.realaicy.prod.jc.modules.auditdb.service;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckModuleVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface PreCheckModuleService extends BaseServiceWithVO<PreCheckModule, BigInteger, PreCheckModuleVO> {

    Short findTopWeightByPID(BigInteger parentID);

    PreCheckModule findRootByTem(BigInteger temID);

    PreCheckModule findRootByTemName(String temName);

    /**
     * 检查是否已经存在temName的模版
     * @param temName 模版名称
     * @return 如果存在，则返回True
     */
    Boolean existTemName(String temName);

    /**
     * 返回所有的模版名称集合
     * @return 所有的模版名称集合
     */
    List<String> getTemNameList();



}
