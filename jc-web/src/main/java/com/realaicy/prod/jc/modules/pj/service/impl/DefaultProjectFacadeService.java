package com.realaicy.prod.jc.modules.pj.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.vo.ApplianceVO;
import com.realaicy.prod.jc.modules.pj.model.vo.ProjectFacadeVO;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import com.realaicy.prod.jc.modules.pj.service.ProjectFacadeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultProjectFacadeService extends DefaultBaseServiceImpl<ProjectFacade, BigInteger>
        implements ProjectFacadeService {


    @Override
    public void saveFromVO(ProjectFacade po, ProjectFacadeVO vo) {

    }
}
