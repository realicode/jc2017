package com.realaicy.prod.jc.modules.auditdb.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.auditdb.model.PreCheckModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface PreCheckModuleRepos extends BaseJPARepository<PreCheckModule, BigInteger> {
    /**
     * @param parentID xxx
     * @return xx
     */
    @Query(value = "SELECT t.RES_WEIGHT FROM jc_m_check_precheckmodule t "
            + "WHERE t.PID = :parentID AND t.F_DELETED = 0 ORDER BY t.RES_WEIGHT DESC Limit 1",
            nativeQuery = true)
    Short findTopWeightByPID(@Param("parentID") BigInteger parentID);

    PreCheckModule findByTemAndRootlevel(BigInteger temID, Boolean isRoot);

    PreCheckModule findByIdAndRootlevel(BigInteger id, Boolean isRoot);


    PreCheckModule findByTemNameAndRootlevel(String temName, Boolean isRoot);

    /**
     * 检查给定的名称的模版的数量
     *
     * @param temName 模版名称
     * @return 模版数量
     */
    @Query(value = "SELECT count(t.TEM_NAME) FROM jc_m_check_precheckmodule t WHERE "
            + " t.TEM_NAME = :temName AND t.F_DELETED = 0",
            nativeQuery = true)
    Integer checkTemName(@Param("temName") String temName);


    /**
     * @return 模版数量
     */
    @Query(value = "SELECT DISTINCT t.TEM_NAME FROM jc_m_check_precheckmodule t WHERE t.F_DELETED = 0",
            nativeQuery = true)
    List<String> temNameList();

}
