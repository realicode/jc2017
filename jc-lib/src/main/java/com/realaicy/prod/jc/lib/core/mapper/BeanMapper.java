package com.realaicy.prod.jc.lib.core.mapper;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * <p/>
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 *
 * @author realaicy
 */
@SuppressWarnings("unused")
public final class BeanMapper {


    /**
     * Do not instantiate BeanMapper.
     */
    private BeanMapper() {

    }

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     *
     * @param <T>              the type parameter
     * @param source           the source
     * @param destinationClass the destination class
     * @return the t
     */
    public static <T> T map(final Object source, final Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     *
     * @param <T>              the type parameter
     * @param sourceList       the source list
     * @param destinationClass the destination class
     * @return the list
     */
    public static <T> List<T> mapList(final Collection sourceList, final Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     *
     * @param source            the source
     * @param destinationObject the destination object
     */
    public static void copy(final Object source, final Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}