package com.realaicy.prod.jc.lib.core.service;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.realaicy.prod.jc.lib.core.data.jpa.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>抽象service层基类 提供一些简便方法
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 * <p>User: Realaicy
 * <p>Date: 16-1-12 下午8:43
 * <p>Version: 1.0
 *
 * @param <M>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings("unused")
public interface BaseService<M extends AbstractEntity, ID extends Serializable> {


    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    boolean exists(ID id);

    /**
     * 实体是否存在
     *
     * @param name 主键
     * @return 存在 返回true，否则false
     */
    Boolean existName(String name);

    /**
     * 统计实体总数
     *
     * @return 实体总数 long
     */
    long count();

    /**
     * 根据条件统计实体总数
     *
     * @return 实体总数 long
     */
    long count(Specification<M> spec);


    /**
     * 保存对象
     *
     * @param entity the entity
     */

    <S extends M> S save(S entity);


    /**
     * 保存对象
     *
     * @param entities the m list
     */
    <S extends M> List<S> save(Iterable<S> entities);

    /**
     * 保存对象
     *
     * @param o the o
     */
    M saveAndFlush(M o);

    /**
     * 更新对象
     *
     * @param o the o
     */
    void update(M o);

    /**
     * 删除对象
     *
     * @param o the o
     */
    void delete(M o);

    /**
     * 根据ID删除对象
     *
     * @param id the id
     */
    void deleteById(ID id);

    /**
     * 批量删除,根据ID
     *
     * @param ids the ids
     */
    void deleteByIds(ID[] ids);

    /**
     * 批量删除,根据实体
     *
     * @param entities the ids
     */

    void deleteInBatch(Collection<M> entities);


    /**
     * 根据ID获得单个对象
     *
     * @param id 主键
     * @return 实体 m
     */
    M findOne(ID id);

    /**
     * 根据查询条件获取单个对象.
     *
     * @param spec 条件
     * @return 实体 m
     */
    M findOne(Specification<M> spec);

    /**
     * 根据查询条件获取单个对象.
     *
     * @param tenantID 租户
     * @param orgID    组织
     * @return 实体 m
     */
    M findMyTree(Long tenantID, Long orgID);

    /**
     * 根据ID集合查询
     *
     * @param ids the ids
     * @return list list
     */
    List<M> findAll(Collection<ID> ids);

    /**
     * 获得对象所有集合
     *
     * @return all all
     */
    List<M> findAll();

    List<M> findAll(Pageable pageable);

    /**
     * 获得对象所有集合,根据查询条件
     *
     * @return all all
     */
    List<M> findAll(Specification<M> spec);

    /**
     * 根据查询条件分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return page page
     */
    List<M> findAll(Specification<M> spec, Pageable pageable);

    //Page<M> findAll(Specification<M> spec, Pageable pageable);


    /**
     * 分页查询
     *
     * @param pageRequest the page request
     * @param hql         the hql
     * @param values      the values
     * @return page page
     */
    Page<M> findPage(PageRequest pageRequest, String hql, Object... values);

    /**
     * 分页查询
     *
     * @param pageRequest the page request
     * @param hql         the hql
     * @param values      the values
     * @return page page
     */
    Page<M> findPage(PageRequest pageRequest, String hql, Map<String, ?> values);

    /**
     * 按照属性条件封装类查询
     *
     * @param pageRequest the page request
     * @param filters     the filters
     * @return page page
     */
    Page<M> findPage(PageRequest pageRequest, List<PropertyFilter> filters);


    /**
     * 按照属性条件封装类查询 不分页
     *
     * @param filters the filters
     * @return list list
     */
    List<M> find(List<PropertyFilter> filters);

}
