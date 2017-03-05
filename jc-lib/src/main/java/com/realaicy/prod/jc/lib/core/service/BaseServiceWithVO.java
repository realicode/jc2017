package com.realaicy.prod.jc.lib.core.service;


import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;
import com.realaicy.prod.jc.lib.core.data.plugin.Commonable;
import com.realaicy.prod.jc.lib.core.model.vo.BaseVO;

import java.io.Serializable;

public interface BaseServiceWithVO<M extends BaseEntity<ID> & Commonable<ID>,
        ID extends Serializable, V extends BaseVO<ID>>
        extends BaseService<M, ID> {

    void saveFromVO(M po, V vo);

}