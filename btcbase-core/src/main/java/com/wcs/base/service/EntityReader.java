package com.wcs.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.util.Validate;


/**
 * JPA Reader 基类.
 * <p/>
 * 扩展功能包括分页查询,按属性过滤条件列表查询.
 * 可直接使用,也可以扩展EntityService
 *
 * @author chris
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EntityReader extends QueryCreator {
	private static final long serialVersionUID = 1L;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    // --------------------------------- 条件查询方法 -----------------------------------//   
    
    /**
     * 按id获取对象.
     */
    public <T> T findUnique(Class<T> entityClass, final Serializable id) {
        Validate.notNull(id, "id不能为空");
        return (T) entityManager.find(entityClass, id);
    }

    /**
     * 按JPQL查询唯一对象.
     *
     * @param values 数量可变的参数,按顺序绑定.
     */
    @SuppressWarnings("unchecked")
	public <X> X findUnique(final String jpql, final Object... values) {
        return (X) createQuery(jpql, values).getSingleResult();
    }

   /**
     * 按JPQL查询唯一对象.
     *
     * @param values 命名参数,按名称绑定.
     */
    @SuppressWarnings("unchecked")
	public <X> X findUnique(final String jpql, final Map<String, ?> values) {
        return (X) createQuery(jpql, values).getSingleResult();
    }

   /**
     * 按JPQL查询对象列表.
     *
     * @param values 数量可变的参数,按顺序绑定.
     */
    @SuppressWarnings("unchecked")
	public <X> List<X> findList(final String jpql, final Object... values) {
        return createQuery(jpql, values).getResultList();
    }

    /**
     * 按JPQL查询对象列表.
     *
     * @param values 命名参数,按名称绑定.
     */
    @SuppressWarnings("unchecked")
	public <X> List<X> findList(final String jpql, final Map<String, ?> values) {
        return createQuery(jpql, values).getResultList();
    }

}
