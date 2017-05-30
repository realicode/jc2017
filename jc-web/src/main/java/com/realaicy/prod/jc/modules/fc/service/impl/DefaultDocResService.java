package com.realaicy.prod.jc.modules.fc.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckModuleRepos;
import com.realaicy.prod.jc.modules.fc.model.DocRes;
import com.realaicy.prod.jc.modules.fc.model.vo.DocResVO;
import com.realaicy.prod.jc.modules.fc.repos.DocResRepos;
import com.realaicy.prod.jc.modules.fc.service.DocResService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultDocResService extends DefaultBaseServiceImpl<DocRes, BigInteger>
        implements DocResService {


    @Override
    public void saveFromVO(DocRes po, DocResVO vo) {

    }

    @Override
    public Short findTopWeightByPID(BigInteger parentID) {
        return ((DocResRepos) baseRepository).findTopWeightByPID(parentID);
    }

    @Override
    public DocRes findMyFile(short resBelongType, Boolean orgRootFlag, BigInteger belongID) {
        return ((DocResRepos) baseRepository).findByResBelongTypeAndOrgRootFlagAndBelongID(resBelongType,orgRootFlag,belongID);
    }
}
