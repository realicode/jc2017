package com.realaicy.prod.jc.modules.pj.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.Application;
import com.realaicy.prod.jc.modules.pj.model.ApplicationVO;
import com.realaicy.prod.jc.modules.pj.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultApplicationService extends DefaultBaseServiceImpl<Application, BigInteger>
        implements ApplicationService {

    @Override
    public void saveFromVO(Application po, ApplicationVO vo) {

    }
}
