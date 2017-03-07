package com.realaicy.prod.jc.modules.system.service;

import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.model.vo.UserVO;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
public interface UserService extends BaseServiceWithVO<User, BigInteger, UserVO> {

    User findByName(String username);

    List<User> findAllUsersWithPage(PageRequest pageRequest);

    List<User> findByUsernameContaining(String username);

    Boolean ifHasNoDelUserByRole(Role role);

    Boolean ifHasNoDelUserByOrg(Org org);

    List<UserVO> convertFromPOListToVOList(List<User> poList);


}
