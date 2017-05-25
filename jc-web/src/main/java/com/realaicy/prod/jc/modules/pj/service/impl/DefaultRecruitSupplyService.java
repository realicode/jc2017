package com.realaicy.prod.jc.modules.pj.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import com.realaicy.prod.jc.modules.pj.model.vo.RecruitSupplyVO;
import com.realaicy.prod.jc.modules.pj.repos.RecruitSupplyRepos;
import com.realaicy.prod.jc.modules.pj.service.RecruitSupplyService;
import com.realaicy.prod.jc.modules.system.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultRecruitSupplyService extends DefaultBaseServiceImpl<RecruitSupply, BigInteger>
        implements RecruitSupplyService {


    @Override
    public void saveFromVO(RecruitSupply po, RecruitSupplyVO vo) {

    }

    @Override
    public List<User> findApplysByPJID(BigInteger pjid) {
        List<BigInteger> supplyids = ((RecruitSupplyRepos) baseRepository).findRecruitSuppliesByPJIDNative(pjid);
        List<User> results = new ArrayList<>();
        for (BigInteger id : supplyids) {
            results.addAll(this.findOne(id).getApplys());
        }
        return results;
    }
}
