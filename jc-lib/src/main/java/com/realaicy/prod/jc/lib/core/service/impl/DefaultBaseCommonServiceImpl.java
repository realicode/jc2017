package com.realaicy.prod.jc.lib.core.service.impl;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.service.BaseCommonService;
import com.realaicy.prod.jc.lib.core.service.OperatorInforService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Realaicy on 2015/6/2.
 * XXX
 *
 * @param <M>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings({"unused"})
public class DefaultBaseCommonServiceImpl<M extends BaseEntity<ID> & Commonable<ID>,
        ID extends Serializable>
        extends DefaultBaseServiceImpl<M, ID>
        implements BaseCommonService<M, ID> {

    @Autowired
    private OperatorInforService<ID> operatorInforService;

    @Override
    public <S extends M> S save(S entity) {
        entity.setCreateTime(new Date());
        entity.setCreaterID(operatorInforService.getOperatorID());
        entity.setUpdateTime(entity.getCreateTime());
        entity.setUpdaterID(entity.getCreaterID());
        return super.save(entity);
    }

    @Override
    public void update(M entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdaterID(operatorInforService.getOperatorID());
        super.update(entity);
    }
}