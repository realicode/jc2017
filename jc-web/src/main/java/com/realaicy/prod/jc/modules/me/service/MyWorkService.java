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

    /**
     * 统计给定用户ID的待办事项数量
     * @param id 用户ID
     * @return 待办事项数量
     */
    Long todoWorkCountByUserId(BigInteger id);

    MyWork findByWorkUri(String workUri);

    /**
     * 返回给定用户名称的待办事项列表
     * @param username 用户名称
     * @return 待办事项列表
     */
    List<MyWork> findTodoWorkByUserUsername(String username);

    /**
     * 将给定的待办事项标记完成
     * @param myWork 待办事项
     */
    void markDone(MyWork myWork);


}
