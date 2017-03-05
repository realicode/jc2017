package com.realaicy.prod.jc.modules.medicinedb;


import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
public class DefaultMedDraService extends DefaultBaseServiceImpl<MedDra, BigInteger>
        implements MedDraService {


    @Override
    public void saveFromVO(MedDra po, MedDraVO vo) {

    }

    @Override
    public List<MedDra> findByLevel(String level) {
        return ((MedDraRepos) baseRepository).findByLevel(level);

    }

    @Override
    public List<MedDra> findByPCode(BigInteger pcode) {
        return ((MedDraRepos) baseRepository).findByPCode(pcode);
    }
}
