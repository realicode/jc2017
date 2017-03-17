package com.realaicy.prod.jc.modules.me.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.me.model.MyWork;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface MyWorkRepos extends BaseJPARepository<MyWork, BigInteger> {


    MyWork findByUserUsername(String username);

    Long countByUserUsername(String username);
}
