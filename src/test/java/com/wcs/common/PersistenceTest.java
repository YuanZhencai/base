package com.wcs.common;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.codehaus.plexus.util.dag.DAG;
import org.junit.Test;

import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstrProperty;

public class PersistenceTest {

	@Test
	public void test() {
		// Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("sample");
        EntityManager em = emf.createEntityManager();
 
        System.out.println("em:" + em);
        
        String name = "yuanzhencai";
        Date date = new Date();
        em.getTransaction().begin();
        WfInstancemstr wf = new WfInstancemstr();
        wf.setCreatedBy(name);
        wf.setCreatedDatetime(date);
        wf.setDefunctInd("N");
        wf.setNo("dadsadadsadadsada");
        wf.setRequestBy(name);
        wf.setStatus("通过");
        wf.setSubmitDatetime(date);
        wf.setType("报送报表流程");
        wf.setUpdatedBy(name);
        wf.setUpdatedDatetime(date);
        
        WfInstancemstrProperty wfp = new WfInstancemstrProperty();
        wfp.setName("FileId");
        wfp.setValue("123456");
		wf.addWfInstancemstrProperty(wfp);
//		List resultList = em.createQuery("select wf from WfInstancemstr wf").getResultList();
//		System.out.println(resultList.size());
        
		em.persist(wf);
        em.getTransaction().commit();
 
	}
	@Test
	public void testSearch() {
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("sample");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("em:" + em);
		
		List<WfInstancemstr> wfs = em.createQuery("select wf from WfInstancemstr wf").getResultList();
		for (WfInstancemstr wf : wfs) {
			System.out.println(wf.getWfInstancemstrProperty("FileId").getValue());
			
		}
		
		
	}

}
