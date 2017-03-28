package com.realaicy.prod.jc.modules.system.service;


import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.pj.model.vo.PreInspectionUserVO;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface UserService  extends BaseServiceWithVO<User, BigInteger, UserVO> {

    User findByUsername(String username);

    Boolean checkUsername(String username);

    /**
     * 查找是否有用户属于给定角色
     * @param roleID 角色主键
     * @return 如果有则返回true
     */
    Boolean ifHasNoDelUserByRole(BigInteger roleID);

    /**
     * 查找是否有用户属于给定机构
     * @param orgID 机构主键
     * @return 如果有则返回true
     */
    Boolean ifHasNoDelUserByOrgID(BigInteger orgID);

    User findByUserinfoWxuserid(String wxUserid);

    List<PreInspectionUserVO> getUserByRole(BigInteger roleID);


}
