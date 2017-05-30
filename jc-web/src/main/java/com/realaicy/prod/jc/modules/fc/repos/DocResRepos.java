package com.realaicy.prod.jc.modules.fc.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.fc.model.DocRes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface DocResRepos extends BaseJPARepository<DocRes, BigInteger> {

    /**
     * @param parentID xxx
     * @return xx
     */
    @Query(value = "SELECT t.RES_WEIGHT FROM jc_m_doc_allinone t "
            + "WHERE t.PID = :parentID AND t.F_DELETED = 0 ORDER BY t.RES_WEIGHT DESC Limit 1",
            nativeQuery = true)
    Short findTopWeightByPID(@Param("parentID") BigInteger parentID);


    DocRes findByResBelongTypeAndOrgRootFlagAndBelongID(short resBelongType, Boolean orgRootFlag, BigInteger belongID);

}
