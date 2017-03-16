package com.realaicy.prod.jc.modules.me.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.model.vo.MyWorkVO;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultMyWorkService extends DefaultBaseServiceImpl<MyWork, BigInteger>
        implements MyWorkService {


    @Override
    public void saveFromVO(MyWork po, MyWorkVO vo) {

    }
}
