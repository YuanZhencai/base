/** * ApplyServiceTest.java 
* Created on 2014年5月16日 下午3:49:14 
*/

package com.wcs.demo.goc.bridge.workflow.service;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wcs.common.util.Deployments;
import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ApplyServiceTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

@RunWith(Arquillian.class)
public class ApplyServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = Deployments.createDeployment();
		System.out.println(archive.toString(true));
		return archive;
	}

	@EJB 
	private ApplyService applyService;
	
	@Test
	public void testCreateFlow() {
		WfInstancemstr wf = applyService.createFlow();
		assertNotNull(wf);
	}

	@Test
	public void testDoDispatch() {
//		fail("Not yet implemented");
	}

}
