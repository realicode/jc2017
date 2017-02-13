package com.realaicy.prod.jc.lib.core.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Realaicy on 2015/5/14.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings("unused")
@NoRepositoryBean
public interface BaseJPARepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 根据主键删除
     *
     * @param ids xxx
     */
    void delete(ID[] ids);

    /**
     * Find one non deleted t.
     *
     * @param id the id
     * @return the t
     */
    T findOneNonDeleted(ID id);

    /**
     * Exist name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    Boolean existName(String name);

    /**
     * Find by name with in a parent t.
     *
     * @param name the name
     * @param pid  the pid
     * @return the t
     */
    T findByNameWithInAParent(String name, BigInteger pid);

    /**
     * Find by org id and org root flag and delete flag t.
     *
     * @param orgID       the org id
     * @param orgRootFlag the org root flag
     * @param deleteFlag  the delete flag
     * @return the t
     */
    T findByOrgIDAndOrgRootFlagAndDeleteFlag(BigInteger orgID, Boolean orgRootFlag, Boolean deleteFlag);

}