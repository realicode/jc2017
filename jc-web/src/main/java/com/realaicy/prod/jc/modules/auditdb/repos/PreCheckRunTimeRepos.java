package com.realaicy.prod.jc.modules.auditdb.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckRunTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface PreCheckRunTimeRepos extends BaseJPARepository<PreCheckRunTime, BigInteger> {

    /**
     * @param preID xxx
     * @param checkModuleID xxx
     * @return xx
     */
    @Query(value = "SELECT t.ID FROM jc_m_check_precheck_runtime t "
            + "WHERE t.PREINSPECTION_ID = :preID AND t.CHECKMODULE_ID = :checkModuleID",
            nativeQuery = true)
    BigInteger findID(@Param("preID") BigInteger preID, @Param("checkModuleID") BigInteger checkModuleID);


}
