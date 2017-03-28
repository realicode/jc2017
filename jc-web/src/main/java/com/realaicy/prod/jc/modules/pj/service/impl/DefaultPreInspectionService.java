package com.realaicy.prod.jc.modules.pj.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionVO;
import com.realaicy.prod.jc.modules.pj.model.vo.ProjectFacadeVO;
import com.realaicy.prod.jc.modules.pj.service.PreInspectionService;
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
public class DefaultPreInspectionService extends DefaultBaseServiceImpl<PreInspection, BigInteger>
        implements PreInspectionService {


    @Override
    public void saveFromVO(PreInspection po, PreInspectionVO vo) {

    }
}
