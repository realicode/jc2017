package com.realaicy.prod.jc.modules.medicinedb;



import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface MedDraRepos extends BaseJPARepository<MedDra, BigInteger> {

    List<MedDra> findByLevel(String level);
    List<MedDra> findByPCode(BigInteger pcode);


}
