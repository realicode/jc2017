package com.realaicy.prod.jc.lib.core.data.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Realaicy on 2015/5/14.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
@SuppressWarnings("unused")
public class SimpleBaseJPARepository<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseJPARepository<T, ID> {

    private Logger logger = LoggerFactory.getLogger(SimpleBaseJPARepository.class);
    /**
     * xxx
     */
    private final EntityManager entityManager;


    /**
     * Instantiates a new Simple base repository.
     *
     * @param domainClass   the domain class
     * @param entityManager the entity manager
     */
// There are two constructors to choose from, either can be used.
    public SimpleBaseJPARepository(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
// This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }

    /**
     * Instantiates a new Simple base jpa repository.
     *
     * @param entityInformation the entity information
     * @param entityManager     the entity manager
     */
    public SimpleBaseJPARepository(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);

        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
    }


    @Override
    public void delete(ID[] ids) {
        //todo
    }

    @SuppressWarnings("unchecked")
    public T findOneNonDeleted(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        Class domainType = this.getDomainClass();

        return (T) entityManager.createQuery("select e from " + domainType.getName()
                + " e where e.id=:id and e.deleteFlag=:deleteFlag").
                setParameter("id", id).setParameter("deleteFlag", false).getSingleResult();

    }

    @Override
    public Boolean existName(String name) {
        int count = Integer.parseInt(entityManager.createQuery("select count(*) from "
                + this.getDomainClass().getName()
                + " e where e.name=:name").
                setParameter("name", name).getSingleResult().toString());
        return count >= 1;

    }

    @SuppressWarnings({"unchecked", "SqlDialectInspection", "SqlNoDataSourceInspection"})
    @Override
    public T findByNameWithInAParent(String name, BigInteger pid) {

        String tableNameOfEntityClass = this.getDomainClass().getAnnotation(Table.class).name();
        logger.info("tableNameOfEntityClass:{}", tableNameOfEntityClass);
        String sql = "select * from " + tableNameOfEntityClass
                + " as t where t.NAME='" + name + "'" + " and t.PID='" + pid + "'" + " and t.F_DELETED=0";
        logger.info("sql:{}", sql);

        try {
            return (T) entityManager.createNativeQuery(sql).getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            logger.info("NoResultException");
            return null;
        }
    }

    @Override
    public T findByOrgIDAndOrgRootFlagAndDeleteFlag(BigInteger orgID, Boolean orgRootFlag, Boolean deleteFlag) {

        //noinspection unchecked
        return (T) entityManager.createQuery("select e from "
                + this.getDomainClass().getName()
                + " e where e.orgID=:orgID and e.orgRootFlag=:orgRootFlag and e.deleteFlag=:deleteFlag").
                setParameter("orgID", orgID).setParameter("orgRootFlag", orgRootFlag)
                .setParameter("deleteFlag", deleteFlag).getSingleResult();
    }

}
