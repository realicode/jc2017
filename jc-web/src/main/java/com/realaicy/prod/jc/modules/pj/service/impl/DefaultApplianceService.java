package com.realaicy.prod.jc.modules.pj.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.model.ApplianceVO;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultApplianceService extends DefaultBaseServiceImpl<Appliance, BigInteger>
        implements ApplianceService {

    @Override
    public void saveFromVO(Appliance po, ApplianceVO vo) {

    }
}
