package com.realaicy.prod.jc.modules.system.service.impl;

import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.model.vo.EventActionVO;
import com.realaicy.prod.jc.modules.system.repos.EventActionRepos;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultEventActionService extends DefaultBaseServiceImpl<EventAction, BigInteger>
        implements EventActionService {

    @Override
    public void saveFromVO(EventAction po, EventActionVO vo) {

    }

    @Override
    public List<EventAction> findByName(String name) {
        return ((EventActionRepos) baseRepository).findByName(name);
    }
}

