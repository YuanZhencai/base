package com.wcs.ncp.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.wcs.base.util.Validate;


/**
 * JPA Service 基类.
 * <p/>
 * 扩展功能包括分页查询,按属性过滤条件列表查询.
 * 可直接使用,也可以扩展EntityService
 *
 * @author chris
 */
@Stateless
public class SubEntityService implements Serializable {
	private static final long serialVersionUID = 1L;

	//@PersistenceContext(unitName = "pu")
	@PersistenceContext(unitName = "pu",type=PersistenceContextType.EXTENDED)
    public EntityManager entityManager;

    // ----------------------------- 基本 CRUD 操作方法 ------------------------------//

    /**
     * 保存新增的对象.
     */
    public <T> T create(final T entity) {
        //Validate.notNull(entity, "entity不能为空");
        entityManager.persist(entity);
        return entity;
    }

    /**
     * 保存修改的对象.
     */
    public <T> T update(final T entity) {
        //Validate.notNull(entity, "entity不能为空");
        entityManager.merge(entity);
        return entity;
    }

    /**
     * 删除对象.
     *
     * @param entity 对象必须是session中的对象或含id属性的transient对象.
     */
    public <T> void delete(final T entity) {
        Validate.notNull(entity, "entity不能为空");
        entityManager.remove(entity);
    }

    /**
     * 按id删除对象.
     */
    
    public void delete(Class<?> entityClass, final Serializable id) {
        delete(find(entityClass, id));
    }

    /**
     * 按id获取对象.
     */
    
    public <T> T find(Class<T> entityClass, final Serializable id) {
        return (T) entityManager.find(entityClass, id);
    }


    // --------------------------------- 条件查询方法 -----------------------------------//
    /**
     * 根据查询JPQL与参数列表创建Query对象.
     * 与find()函数可进行更加灵活的操作.
     *
     * @param values 数量可变的参数,按顺序绑定.
     */
    
    public Query createQuery(final String queryString, final Object... values) {
        //Validate.hasText(queryString, "queryString不能为空");
        Query query = entityManager.createQuery(queryString);
        if (values != null) {
            for (int i = 1; i <= values.length; i++) {
                query.setParameter(i, values[i - 1]);
            }
        }
        return query;
    }
    
    public <X> List<X> find(final String jpql, final Object... values) {
        return createQuery(jpql, values).getResultList();
    }

    public <X> List<X> findBook(){
    	Query query = entityManager.createQuery("from Prediction");
    	return query.getResultList();
    }
    

}
