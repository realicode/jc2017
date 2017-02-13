package com.realaicy.prod.jc.modules.demo.respos.jpa;

import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.demo.model.DemoEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface DemoRepos extends BaseJPARepository<DemoEntity, BigInteger> {

    /**
     * Find by username demo entity.
     *
     * @param username the username
     * @return the demo entity
     */
    DemoEntity findByUsername(String username);

    /**
     * Find top 10 by username containing list.
     *
     * @param username the username
     * @return the list
     */
    List<DemoEntity> findTop10ByUsernameContaining(String username);

}
