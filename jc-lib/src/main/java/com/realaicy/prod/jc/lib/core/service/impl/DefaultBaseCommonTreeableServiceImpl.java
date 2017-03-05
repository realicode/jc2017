package com.realaicy.prod.jc.lib.core.service.impl;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.data.plugin.Treeable;
import com.realaicy.prod.jc.lib.core.service.BaseCommonTreeableService;
import java.io.Serializable;

/**
 * Created by Realaicy on 2015/6/2.
 * XXX
 *
 * @param <M>  the type parameter
 * @param <ID> the type parameter
 */
public class DefaultBaseCommonTreeableServiceImpl<M extends BaseEntity<ID>
        & Commonable<ID> & Treeable<ID>, ID extends Serializable>
        extends DefaultBaseCommonServiceImpl<M, ID>
        implements BaseCommonTreeableService<M, ID> {
}