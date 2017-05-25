package com.realaicy.prod.jc.modules.pj.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import com.realaicy.prod.jc.modules.pj.model.vo.RecruitSupplyVO;
import com.realaicy.prod.jc.modules.system.model.User;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface RecruitSupplyService extends BaseServiceWithVO<RecruitSupply, BigInteger, RecruitSupplyVO> {

    List<User> findApplysByPJID(BigInteger pjid);
}
