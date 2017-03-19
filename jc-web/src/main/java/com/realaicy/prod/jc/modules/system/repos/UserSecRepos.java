package com.realaicy.prod.jc.modules.system.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface UserSecRepos extends BaseJPARepository<UserSec, BigInteger> {

    //    @Cacheable(key = "#username",
//            cacheResolver = "runtimeCacheResolver")
    @Cacheable(key = "#p0", cacheNames = "UserSec")
    UserSec findByUsername(String username);

}
