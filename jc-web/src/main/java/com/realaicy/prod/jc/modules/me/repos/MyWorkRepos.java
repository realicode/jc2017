package com.realaicy.prod.jc.modules.me.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.me.model.MyWork;

import java.math.BigInteger;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface MyWorkRepos extends BaseJPARepository<MyWork, BigInteger> {

    /**
     *根据用户名，查找该用户的待办工作数量
     * @param username 用户名
     * @return 该用户的待办工作数量
     */
    Long countByUserUsername(String username);

    /**
     *根据用户ID，查找该用户的待办工作数量
     * @param id 该用户的ID
     * @return 该用户的待办工作数量
     */
    Long countByUserId(BigInteger id);

}