package com.realaicy.prod.jc.modules.system.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.vo.OrgVO;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface OrgService extends BaseServiceWithVO<Org, BigInteger, OrgVO> {

    /**
     * 检查给定名称的机构是否存在
     * @param orgName 机构名称
     * @return 如果存在则返回true, 否则返回false
     */
    Boolean checkOrgName(String orgName);

    /**
     * 检查一个给定的机构是否能够被删除
     * @param orgID 机构主键
     * @return 如果可以则返回  true
     */
    boolean canBeDelete(BigInteger orgID);

}
