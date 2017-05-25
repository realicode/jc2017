package com.realaicy.prod.jc.modules.auditdb.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckModuleVO;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckModuleRepos;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckModuleService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
public class DefaultPreCheckModuleService extends DefaultBaseServiceImpl<PreCheckModule, BigInteger>
        implements PreCheckModuleService {


    @Override
    public Short findTopWeightByPID(BigInteger parentID) {
        return ((PreCheckModuleRepos) baseRepository).findTopWeightByPID(parentID);
    }

    @Override
    public PreCheckModule findRootByTem(BigInteger temID) {
        return ((PreCheckModuleRepos) baseRepository).findByTemAndRootlevel(temID, true);
    }

    @Override
    public PreCheckModule findRootByTemName(String temName) {
        return ((PreCheckModuleRepos) baseRepository).findByTemNameAndRootlevel(temName, true);
    }

    @Override
    public Boolean existTemName(String temName) {
        return ((PreCheckModuleRepos) baseRepository).checkTemName(temName) > 0;
    }

    @Override
    public List<String> getTemNameList() {
        return ((PreCheckModuleRepos) baseRepository).temNameList();
    }

    @Override
    public void saveFromVO(PreCheckModule po, PreCheckModuleVO vo) {

    }
}
