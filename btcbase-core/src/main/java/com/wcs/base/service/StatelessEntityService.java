package com.wcs.base.service;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 无状态的实体操作类.
 * <p/>
 * 扩展功能包括分页查询,按属性过滤条件列表查询.
 * 可直接使用,也可以扩展EntityService
 *
 * @author chris
 */
@Named(value = "statelessEntityService")
public class StatelessEntityService extends EntityService {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pu")
	public EntityManager entityManager;

	@SuppressWarnings("unused")
	@PostConstruct
	private void initEntityManager() {
		logger.info("初始化 EntityManager");
		this.setEntityManager(entityManager);
	}
}
