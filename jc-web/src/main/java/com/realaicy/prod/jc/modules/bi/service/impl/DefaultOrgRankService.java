package com.realaicy.prod.jc.modules.bi.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.bi.model.OrgRank;
import com.realaicy.prod.jc.modules.bi.model.vo.OrgRankVO;
import com.realaicy.prod.jc.modules.bi.service.OrgRankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultOrgRankService extends DefaultBaseServiceImpl<OrgRank, BigInteger>
        implements OrgRankService {


    @Override
    public void saveFromVO(OrgRank po, OrgRankVO vo) {

    }
}
