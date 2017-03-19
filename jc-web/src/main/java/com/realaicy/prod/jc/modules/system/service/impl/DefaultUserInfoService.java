package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.UserInfo;
import com.realaicy.prod.jc.modules.system.model.vo.UserInfoVO;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultUserInfoService extends DefaultBaseServiceImpl<UserInfo, BigInteger>
        implements UserInfoService {


    @Override
    public void saveFromVO(UserInfo po, UserInfoVO vo) {

    }
}
