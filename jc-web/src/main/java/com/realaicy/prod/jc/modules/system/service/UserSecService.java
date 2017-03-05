package com.realaicy.prod.jc.modules.system.service;


import com.realaicy.prod.jc.lib.core.service.BaseService;
import com.realaicy.prod.jc.modules.system.model.UserSec;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface UserSecService extends BaseService<UserSec, BigInteger> {

    UserSec findByUsername(String username);
}
