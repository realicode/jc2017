package com.realaicy.prod.jc.modules.me.service;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.model.vo.MyWorkVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface MyWorkService extends BaseServiceWithVO<MyWork, BigInteger, MyWorkVO> {

    Long countByUserUsername(String username);

    Long countByUserId(BigInteger id);

    Long todoWorkCountByUserId(BigInteger id);

    MyWork findByWorkUri(String workUri);

    List<MyWork> findTodoWorkByUserUsername(String username);


}
