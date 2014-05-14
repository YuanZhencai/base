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
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;

public class PersistenceTest {

	@Test
	public void testPostgre() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample_postgre");
		EntityManager em = emf.createEntityManager();

		System.out.println("em:" + em);
		em.getTransaction().begin();

		String name = "yuanzhencai";
		Date date = new Date();
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
		wfp.setName("company");
		wfp.setValue("wcs");
		wf.addWfInstancemstrProperty(wfp);
		em.persist(wf);

		em.getTransaction().commit();

		List<WfInstancemstr> wfs = em.createQuery("select wf from WfInstancemstr wf").getResultList();
		for (WfInstancemstr wfInstancemstr : wfs) {
			System.out.println(wfInstancemstr.getWfInstancemstrProperty("FileId").getValue());
		}

	}

	@Test
	public void testDB2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample_db2");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		String name = "yuanzhencai";
		Date date = new Date();
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
		wf.put("company", "wcs");
		
		WfStepmstr step = new WfStepmstr();
		step.setFromStepId(0);
		step.setChargedBy(name);
		step.setCode("code");
		step.setCompletedDatetime(date);
		step.setCreatedBy(name);
		step.setCreatedDatetime(date);
		step.setDealMethod("dealMethod");
		step.setDefunctInd("N");
		step.setName("上传报表节点");
		step.setUpdatedBy(name);
		step.setUpdatedDatetime(date);
		step.put("file", "{dasdasdasdadssa}");
		step.setWfInstancemstr(wf);
		
		wf.addWfStepmstr(step);

		em.persist(wf);

		em.getTransaction().commit();

	}

	@Test
	public void testSaveDB2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample_db2");
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

		em.persist(wf);

		em.getTransaction().commit();

	}
}
