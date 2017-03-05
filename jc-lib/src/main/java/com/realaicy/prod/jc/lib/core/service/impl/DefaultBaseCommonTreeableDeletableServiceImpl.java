package com.realaicy.prod.jc.lib.core.service.impl;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.data.plugin.LogicDeletable;
import com.realaicy.prod.jc.lib.core.data.plugin.Treeable;
import com.realaicy.prod.jc.lib.core.service.BaseCommonTreeableDeletableService;

import java.io.Serializable;

/**
 * Created by Realaicy on 2015/6/2.
 * XXX
 *
 * @param <M>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings({"unused"})
public class DefaultBaseCommonTreeableDeletableServiceImpl<M extends BaseEntity<ID>
        & Commonable<ID> & Treeable<ID> & LogicDeletable, ID extends Serializable>
        extends DefaultBaseCommonTreeableServiceImpl<M, ID>
        implements BaseCommonTreeableDeletableService<M, ID> {
    @Override
    public <S extends M> S save(S entity) {
        entity.markDeleted();
        return super.save(entity);
    }
}