package com.realaicy.prod.jc.modules.me.repos;


import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.me.model.MyWork;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
public interface MyWorkRepos extends BaseJPARepository<MyWork, BigInteger> {


    MyWork findByWorkUri(String workUri);

    /**
     * 根据用户名，查找该用户的待办工作数量
     *
     * @param username 用户名
     * @return 该用户的待办工作数量
     */
    Long countByUserUsername(String username);

    /**
     * 根据用户ID，查找该用户的待办工作数量
     *
     * @param userID 该用户的ID
     * @return 该用户的待办工作数量
     */
    Long countByUserIdAndStatus(BigInteger userID, short status);

    Long countByUserId(BigInteger userID);

    List<MyWork> findByUserUsername(String username);

    List<MyWork> findByUserUsernameAndStatus(String username, Short status);


}
