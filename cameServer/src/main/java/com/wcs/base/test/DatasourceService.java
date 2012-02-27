/**
 * <P>Project: cameServer</P>
 * <P>Description: </P>
 * <P>Copyright (c) 2012 Wilmar Consultancy Services</P>
 * <P>All Rights Reserved.</P>
 * @author <a href="mailto:yansong@wcs-global.com">$Author$</a>
 */
package com.wcs.base.test;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class DatasourceService.
 */
@Stateless
public class DatasourceService implements Serializable{
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The em. */
	@PersistenceContext
    EntityManager em;
    
    /**
     * Gets the datasource by id.
     *
     * @param datasourceMstrId the datasource mstr id
     * @return the datasource by id
     */
    public Datasourcemstr getDatasourceById(long datasourceMstrId) {
    	log.info("---------------------Start: getDatasourceById-----------------");
    	Datasourcemstr datasourcemstr=(Datasourcemstr) em.find(Datasourcemstr.class, datasourceMstrId);
    	log.info("---------------------End: getDatasourceById-----------------");
        return datasourcemstr;
    }
    
    /**
     * Creat datasource.
     *
     * @param datasourcemstr the datasourcemstr
     */
    public void creatDatasource(Datasourcemstr datasourcemstr) {
    	log.info("---------------------Start: creatDatasource-----------------");
        em.persist(datasourcemstr);
        log.info("---------------------End: creatDatasource-----------------");
    }
    
    /**
     * Delete datasource by id.
     *
     * @param datasourceMstrId the datasource mstr id
     */
    public void deleteDatasourceById(long datasourceMstrId) {
    	log.info("---------------------Start: deleteDatasourceById-----------------");
        em.remove(getDatasourceById(datasourceMstrId));
        log.info("---------------------End: deleteDatasourceById-----------------");
    }
    
    /**
     * Update datasource.
     *
     * @param datasourcemstr the datasourcemstr
     */
    public void updateDatasource(Datasourcemstr datasourcemstr) {
    	log.info("---------------------Start: updateDatasource-----------------");
        em.merge(datasourcemstr);
        log.info("---------------------End: updateDatasource-----------------");
    }
    
    /**
     * Gets the datasource list all.
     *
     * @return the datasource list all
     */
    public List<Datasourcemstr> getDatasourceListAll() {
    	log.info("---------------------Start: getDatasourceListAll-----------------");
    	String sql = "select d from Datasourcemstr d";
    	List<Datasourcemstr> result =em.createQuery(sql, Datasourcemstr.class).getResultList();
        log.info("---------------------End: getDatasourceListAll , size " + result.size() + "-----------------");
        return result;

    }
    
    /**
     * Gets the datasource single.
     *
     * @param datasourcemstrId the datasourcemstr id
     * @return the datasource single
     */
    public Datasourcemstr getDatasourceSingle(long datasourcemstrId) {
    	log.info("---------------------Start: getDatasourceSingle-----------------");
    	String sql = "select d from Datasourcemstr d where d.datasourcemstrId = :datasourcemstrId";
    	Datasourcemstr result = em.createQuery(sql, Datasourcemstr.class).setParameter("datasourcemstrId", datasourcemstrId).getSingleResult();
    	log.info("---------------------End: getDatasourceSingle -----------------");
        return result;
    }
    
}
