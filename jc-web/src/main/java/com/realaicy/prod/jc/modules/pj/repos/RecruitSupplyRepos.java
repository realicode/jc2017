package com.realaicy.prod.jc.modules.pj.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.pj.model.RecruitSupply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface RecruitSupplyRepos extends BaseJPARepository<RecruitSupply, BigInteger> {

    /**
     *
     * @param pjID xxx
     * @return xx
     */
    @Query(value = "SELECT t.ID FROM jc_m_recruit_supply t "
            + "WHERE t.PJ_ID = :pjID", nativeQuery = true)
    List<BigInteger> findRecruitSuppliesByPJIDNative(@Param("pjID") BigInteger pjID);


}
