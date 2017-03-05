package com.realaicy.prod.jc.modules.medicinedb;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface MedDraService extends BaseServiceWithVO<MedDra, BigInteger, MedDraVO> {

    List<MedDra> findByLevel(String level);
    List<MedDra> findByPCode(BigInteger pcode);

}
