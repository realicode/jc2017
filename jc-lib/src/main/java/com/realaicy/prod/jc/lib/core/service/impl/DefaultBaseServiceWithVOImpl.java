package com.realaicy.prod.jc.lib.core.service.impl;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;
import com.realaicy.prod.jc.lib.core.service.BaseServiceWithVO;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

import static com.realaicy.prod.jc.lib.core.utils.RealBeanUtils.getNullPropertyNames;

/**
 * Created by Realaicy on 2015/6/2.
 * XXX
 *
 * @param <M>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings({"unused"})
public class DefaultBaseServiceWithVOImpl<M extends BaseEntity<ID> & Commonable<ID>,
        ID extends Serializable, V extends BaseVO<ID>>
        extends DefaultBaseServiceImpl<M, ID>
        implements BaseServiceWithVO<M, ID, V> {


    @Override
    public void saveFromVO(M po, V vo) {

        BeanUtils.copyProperties(vo, po, getNullPropertyNames(vo));
    }
}