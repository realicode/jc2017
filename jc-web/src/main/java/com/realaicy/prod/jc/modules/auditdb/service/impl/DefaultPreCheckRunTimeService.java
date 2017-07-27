package com.realaicy.prod.jc.modules.auditdb.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckRunTime;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckRunTImeVO;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckRunTimeRepos;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckRunTimeService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
public class DefaultPreCheckRunTimeService extends DefaultBaseServiceImpl<PreCheckRunTime, BigInteger>
        implements PreCheckRunTimeService {



    @Override
    public void saveFromVO(PreCheckRunTime po, PreCheckRunTImeVO vo) {

    }

    @Override
    public PreCheckRunTime realfind(BigInteger preID, BigInteger checkModuleID) {

        return ((PreCheckRunTimeRepos) baseRepository).findOne(((PreCheckRunTimeRepos) baseRepository).findID(preID, checkModuleID));
    }
}
