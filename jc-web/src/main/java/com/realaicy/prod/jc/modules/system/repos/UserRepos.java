package com.realaicy.prod.jc.modules.system.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.User;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface UserRepos extends BaseJPARepository<User, BigInteger> {

    User findByUsername(String username);

    List<User> findTop10ByUsernameContaining(String username);

}
