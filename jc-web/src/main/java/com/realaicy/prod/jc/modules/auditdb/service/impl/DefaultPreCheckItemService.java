package com.realaicy.prod.jc.modules.auditdb.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItem;
import com.realaicy.prod.jc.modules.auditdb.model.vo.PreCheckItemVO;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRepos;
import com.realaicy.prod.jc.modules.auditdb.service.PreCheckItemService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
public class DefaultPreCheckItemService extends DefaultBaseServiceImpl<PreCheckItem, BigInteger>
        implements PreCheckItemService {


    @Override
    public void saveFromVO(PreCheckItem po, PreCheckItemVO vo) {

    }

    @Override
    public List<PreCheckItem> findByModuleID(BigInteger moduleid) {
        return ((PreCheckItemRepos) baseRepository).findByModuleID(moduleid);
    }
}
